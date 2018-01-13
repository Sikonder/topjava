var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        data: $("#filter").serialize(),
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

// $(function () {
//     datatableApi = $("#datatable").DataTable({
//         "paging": false,
//         "info": true,
//         "columns": [
//             {
//                 "data": "dateTime"
//             },
//             {
//                 "data": "description"
//             },
//             {
//                 "data": "calories"
//             },
//             {
//                 "defaultContent": "Edit",
//                 "orderable": false
//             },
//             {
//                 "defaultContent": "Delete",
//                 "orderable": false
//             }
//         ],
//         "order": [
//             [
//                 0,
//                 "desc"
//             ]
//         ]
//     });
//     makeEditable();
// });

$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime",
                "render": function (date) {
                    if (date) {
                        return date.substring(0, 10);
                    }
                    return date;
                }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"

            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).addClass("disabled");
            }
        },
        "initComplete": makeEditable
    });
});