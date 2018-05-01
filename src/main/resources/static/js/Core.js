var Core = {
    click: function (id, click) {
        $("#" + id).click(function () {
            click();
        });
    },
    ajaxGet: function (url, success) {
        $.ajax({
            url: url,
            type: "GET",
            dataType: "json",
            success: function (e) {
                success(e);
            },
            error: function (e) {
                console.info(e);
            }
        });
    },
    ajaxPost: function (url, data, success) {
        $.ajax({
            url: url,
            type: "post",
            data: data,
            dataType: "json",
            success: function (e) {
                success(e);
            },
            error: function (e) {
                console.info(e);
            }
        });
    },
    loadPage: function (url, pageName) {
        $("#pageTitle").html(pageName);
        $("#pageName").html(pageName);
        $("#pageContent").load(url);
    },
    getContent: function () {
        return UE.getEditor('editor').getPlainTxt()
    },
    setContent: function (value) {
        UE.getEditor('editor').execCommand('insertHtml', value)
    },
    setDisabled: function () {
        UE.getEditor('editor').setDisabled('fullscreen');
    },
    setEnabled: function () {
        UE.getEditor('editor').setEnabled();
    },
    radioSetting: function (name, click) {
        var setting = {
            callback: {
                onClick: click
            },
            check: {
                enable: true,
                chkStyle: "radio",
                radioType: "all"
            },
            data: {
                key: {
                    name: name
                },

                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: -1
                }
            }
        };
        return setting;
    },
    checkboxSetting: function (name, click) {
        var setting = {
            callback: {
                onClick: click
            },
            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType: {"Y": "p", "N": "s"}
            },
            data: {
                key: {
                    name: name
                },
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: -1
                }
            }
        };
        return setting;
    },
    initTree: function (id, setting, nodes) {
        var tree = $.fn.zTree.init($("#" + id), setting, nodes);
        tree.expandAll(true);
    },
    treeInfo: function (id) {
        return $.fn.zTree.getZTreeObj(id);
    },
    table: function (id, url, col) {
        $('#' + id).bootstrapTable({
            method: 'post',
            contentType: "application/x-www-form-urlencoded",//必须要有！！！！
            url: url,//要请求数据的文件路径
            height: 450,//高度调整
            //toolbar: '#toolbar',
            striped: true,
            dataField: "res",
            pageNumber: 1, //初始化加载第一页，默认第一页
            pagination: true,//是否分页
            queryParamsType: 'limit',//查询参数组织方式
            //queryParams: queryParams,//请求服务器时所传的参数
            sidePagination: 'server',//指定服务器端分页
            pageSize: 5,//单页记录数
            pageList: [5, 10, 20, 30],//分页步进值
            showRefresh: true,//刷新按钮
            showColumns: true,
            clickToSelect: true,//是否启用点击选中行
            toolbarAlign: 'right',
            buttonsAlign: 'right',//按钮对齐方式
            columns: col,
            locale: 'zh-CN',//中文支持,
            responseHandler: function (res) {
                return res;
            }
        });
    },
    tableRefresh : function(id){
        $('#' + id).bootstrapTable('refresh');
    },
    toastrSuccess: function (msg) {
        toastr.success(msg, '通知消息', {
            timeOut: 5000,
            "closeButton": true,
            "debug": false,
            "newestOnTop": true,
            "progressBar": true,
            "positionClass": "toast-top-right",
            "preventDuplicates": true,
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut",
            "tapToDismiss": false

        })
    },
    toastrWarning: function (msg) {
        toastr.warning(msg, '警告消息', {
            "positionClass": "toast-top-right",
            timeOut: 5000,
            "closeButton": true,
            "debug": false,
            "newestOnTop": true,
            "progressBar": true,
            "preventDuplicates": true,
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut",
            "tapToDismiss": false

        })
    },
    toastrError: function (msg) {
        toastr.error(msg, '错误消息', {
            "positionClass": "toast-top-right",
            timeOut: 5000,
            "closeButton": true,
            "debug": false,
            "newestOnTop": true,
            "progressBar": true,
            "preventDuplicates": true,
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut",
            "tapToDismiss": false

        })
    },
    alertWarning: function (title, text, success) {
        swal({
                title: title,
                text: text,
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText: "取消",
                closeOnConfirm: true,
                closeOnCancel: true
            },
            function (isConfirm) {
                if (isConfirm) {
                    success();
                }
            });
    }
};