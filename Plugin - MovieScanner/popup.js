document.addEventListener('DOMContentLoaded', function () {
    var link = document.getElementById('link');
    link.addEventListener('click', function () {
        var url = "http://localhost:4200/movies";
        console.log(url);
        chrome.tabs.create({ url: url });
    });
});
var checkbox = document.getElementById('checkbox');
checkbox.checked = true;

document.addEventListener('DOMContentLoaded', function () {
    var checkbox = document.getElementById('checkbox');
    checkbox.addEventListener('change', function () {
        if (this.checked) {
            toggleExtension(true);
        } else {
            toggleExtension(false);
        }
    });
});


function toggleExtension(enable) {
    var extensionId = "edpmjoellbnpohdeaicmfbjjlnpkjjef"; 

    chrome.management.setEnabled(extensionId, enable, function () {
        if (enable) {
            console.log("Extension enabled");
        } else {
            console.log("Extension disabled");
        }
    });
}