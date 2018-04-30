var ajaxUrl = "ajax/admin/users/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

function changeStatus(id) {

    var chbox;
    chbox = document.getElementById('checking');
    var enabled;
    if (chbox.checked) {
        enabled = true;
    }
    else {
        enabled = false;
    }
    $.ajax({
        type: "POST",
        url: ajaxUrl + id,
        data: "enabled=" + enabled,
    }).done(function () {
        $("#"+id).toggleClass("disabled");
        //successNoty(enabled ? "common.enabled" : "common.disabled");
    });

}

