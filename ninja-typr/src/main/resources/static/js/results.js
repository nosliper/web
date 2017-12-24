window.addEventListener("unload", function(event) {
    event.preventDefault();
    window.location.href = "/index";
    return false;
});