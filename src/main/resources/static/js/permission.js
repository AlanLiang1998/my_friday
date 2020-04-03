function checkPermission() {
    var pers = [];
    var permissions = jQuery.parseJSON(localStorage.permission);
    $("[permission]").each(function () {
        var per = $(this).attr("permission");
        if ($.inArray(per, permissions) < 0) {
            $(this).hide();
        }
    });

    return pers;
}

function hasPermission(permission) {
    var pers = [];
    var permissions = jQuery.parseJSON(localStorage.permission);
    if ($.inArray(permission, permissions) < 0) {
        return false;
    }
    return true;
}

function checkPermissionForTable() {
    var permissions = jQuery.parseJSON(localStorage.permission);
    $("[permission]").each(function () {
        var per = $(this).attr("permission");
        if ($.inArray(per, permissions) >= 0) {
            return true;
        }
    });
    return false;
}