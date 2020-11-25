<#ftl encoding="UTF-8"/>
<#import "parts/base.ftl" as p/>
<@p.page>

    <title>Catalog</title>


    <div class="main_container">
        <h1 style="text-align:center;">Catalog</h1>

        <p style="padding-top:2rem; padding-bottom:2rem;">
            <input class="form-control" type="search" placeholder="Search" aria-label="Search"
                   style="width:30%; margin-left:35%;" id="query" oninput="f()"/>
        </p>


        <div class="my-container">

            <div id="res" class="row">
                <#list items as item>
                    <div class="col-sm-3">
                        <div class="card">
                            <div class="card-header" style="text-align: center">${item.getLabel()}</div>
                            <img class="card-img-top" src="${item.getPhotoPath()}">
                            <p class="card-text" style="text-align: center">${item.getDescription()}</p>
                            <p class="card-text" style="text-align: center"><strong>Price: ${item.getPrice()}$</strong>
                            </p>
                            <form name="my-form" onsubmit="return validform()" action="addToCart" method="post">
                                <input type="hidden" value="${item.getId()}" name="id"/>
                                <input type="hidden" value="${item.getLabel()}" name="label"/>
                                <input type="hidden" value="${item.getPrice()}" name="price"/>
                                <button class="btn btn-primary btn-block my-4" type="submit">Add to cart</button>
                            </form>
                        </div>
                    </div>
                </#list>
            </div>

            <script type="application/javascript">
                function f() {
                    if ($("#query").val().length >= 1) {
                        $.ajax({
                            url: "/catalogSearch",
                            data: {"query": $("#query").val()},
                            dataType: "json",
                            success: function (msg) {
                                if (msg.objects.length > 0) {
                                    $("#res").html("");
                                    for (var i = 0; i < msg.objects.length; i++) {
                                        $("#res").append("<div class=\"col-sm-3\"><div class=\"card\"><div class=\"card-header\" style=\"text-align: center\">" + msg.objects[i].label + "</div><img class=\"card-img-top\" src=\"" + msg.objects[i].photoPath + "\"><p class=\"card-text\" style=\"text-align: center\">" + msg.objects[i].description + "</p><p class=\"card-text\" style=\"text-align: center\"><strong>Price: " + msg.objects[i].price + "</strong></p><form name=\"my-form\" action=\"addToCart\" method=\"post\"><input type=\"hidden\" value=\"" + msg.objects[i].id + "\" name=\"id\"/><input type=\"hidden\" value=\"" + msg.objects[i].label + "\" name=\"label\"/><input type=\"hidden\" value=\"" + msg.objects[i].price + "\" name=\"price\"/><button class=\"btn btn-primary btn-block my-4\" type=\"submit\">Add to cart</button></form></div></div>");
                                    }
                                } else {
                                    $("#res").html("<h2>No results</h2>");
                                }
                            }
                        })
                    } else {
                        $.ajax({
                            url: "/catalogSearch",
                            data: {"query": $("#query").val()},
                            dataType: "json",
                            success: function (msg) {
                                if (msg.objects.length > 0) {
                                    $("#res").html("");
                                    for (var i = 0; i < msg.objects.length; i++) {
                                        $("#res").append("<div class=\"col-sm-3\"><div class=\"card\"><div class=\"card-header\" style=\"text-align: center\">" + msg.objects[i].label + "</div><img class=\"card-img-top\" src=\"" + msg.objects[i].photoPath + "\"><p class=\"card-text\" style=\"text-align: center\">" + msg.objects[i].description + "</p><p class=\"card-text\" style=\"text-align: center\"><strong>Price: " + msg.objects[i].price + "</strong></p><form name=\"my-form\" action=\"addToCart\" method=\"post\"><input type=\"hidden\" value=\"" + msg.objects[i].id + "\" name=\"id\"/><input type=\"hidden\" value=\"" + msg.objects[i].label + "\" name=\"label\"/><input type=\"hidden\" value=\"" + msg.objects[i].price + "\" name=\"price\"/><button class=\"btn btn-primary btn-block my-4\" type=\"submit\">Add to cart</button></form></div></div>");
                                    }
                                } else {
                                    $("#res").html("<h2>No items in catalog yet</h2>");
                                }
                            }
                        })
                    }
                }
            </script>

        </div>
    </div>

</@p.page>