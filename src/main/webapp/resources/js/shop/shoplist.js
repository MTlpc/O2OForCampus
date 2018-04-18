$(function(){

    getList();

});

function getList(e){
    $.ajax({
        url:"/shopadmin/getshoplist",
        type:"get",
        dataType:"json",
        success:function(data){
            if(data.success){
                handleList(data.shopList);
                handleUser(data.user);
            }
        }
    });
}

function handleUser(data){
    $('#user-name').text(data.name);
}

function handleList(data) {
    var html = '';
    data.map(function(item, index) {
        if(index % 2 == 0){
            html += '<div class="row row-shop" style="background-color:rgb(203,200,189)">';
        } else {
            html += '<div class="row row-shop">';
        }
        html += '<div class="col-40">'
            + item.shopName + '</div><div class="col-40">'
            + shopStatus(item.enableStatus)
            + '</div><div class="col-20">'
            + goShop(item.enableStatus, item.shopId) + '</div></div>';

    });
    $('.shop-wrap').html(html);
}

function shopStatus(status) {
    if (status == 0) {
        return '审核中';
    } else if (status == -1) {
        return '店铺非法';
    } else if (status == 1) {
        return '审核通过';
    }
}

function goShop(status, id) {
    if (status == 1) {
        return '<a href="/shopadmin/shopmanagement?shopId=' + id
            + '">进入</a>';
    } else {
        return '';
    }
}