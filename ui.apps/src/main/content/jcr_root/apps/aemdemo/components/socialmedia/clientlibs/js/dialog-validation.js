(function ($, Coral) {
    "use strict";
    $(document).on("dialog-ready", function () {
        var urlField = $("input[name='./embedUrl']");
        urlField.on("change", function () {
            var url = $(this).val();
            var validPattern = /^(https?:\/\/)?(www\.)?(twitter|facebook|instagram)\.com/;
            $(this).siblings(".coral-Form-fielderror").remove();
            $(this).closest(".coral-Form-fieldwrapper").removeClass("is-invalid");
            if (url && !validPattern.test(url)) {
                $("<span class='coral-Form-fielderror'>Invalid social media URL</span>").insertAfter($(this));
                $(this).closest(".coral-Form-fieldwrapper").addClass("is-invalid");
            }
        });
    });
 })(jQuery, Coral);