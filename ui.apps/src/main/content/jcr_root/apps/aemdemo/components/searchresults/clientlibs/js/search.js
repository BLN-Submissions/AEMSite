document.addEventListener("DOMContentLoaded", function () {
    let searchButton = document.getElementById("search-button");
    let searchQueryInput = document.getElementById("search-query");
    let searchResultsContainer = document.getElementById("search-results");
    
    if (searchButton && searchQueryInput) {
        searchButton.addEventListener("click", function () {
            let query = searchQueryInput.value.trim();
            if (query) {
                // Send Analytics Event to AEM DataLayer
                window.adobeDataLayer.push({
                    event: "searchPerformed",
                    searchQuery: query,
                    timestamp: new Date().toISOString()
                });
                // Fetch Results (if using AJAX)
                fetchResults(query, 1);
            }
        });
    }
    
    function fetchResults(query, page) {
        let apiUrl = `/bin/search?query=${encodeURIComponent(query)}&page=${page}`;
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                searchResultsContainer.innerHTML = "";
                if (data.results.length > 0) {
                    let resultsList = document.createElement("ul");
                    data.results.forEach(result => {
                        let listItem = document.createElement("li");
                        listItem.innerHTML = `
                        <a href="${result.link}" target="_blank" class="search-result-link">${result.title}</a>
                        <p>${result.snippet}</p>
                        `;
                        resultsList.appendChild(listItem);
                        // Track Clicks on Search Results
                        listItem.querySelector(".search-result-link").addEventListener("click", function () {
                            window.adobeDataLayer.push({
                                event: "searchResultClicked",
                                resultTitle: result.title,
                                resultURL: result.link,
                                timestamp: new Date().toISOString()
                            });
                        });
                    });
                    searchResultsContainer.appendChild(resultsList);
                } else {
                    searchResultsContainer.innerHTML = "<p>No results found.</p>";
                }
            })
            .catch(error => console.error("Search API Error:", error));
    }
 });