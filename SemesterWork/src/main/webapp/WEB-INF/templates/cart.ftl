<#ftl encoding="UTF-8"/>
<#import "parts/base.ftl" as p/>
<@p.page>

    <title>Cart</title>


    <div class="main_container">
        <h1 style="text-align:center;"> Your cart:</h1>
        <#if items?has_content>
        <h2 style="padding-left:7%;">Total price: ${sum}$</h2>
        <h2 style="padding-left:7%;">
            <form name="my-form" action="makeOrder" method="post">
                <#list numbers as number >
                    <input type="hidden" name="item${number}" value="${number}">
                </#list>
                <button type="submit" class="btn btn-primary">Make order!</button>
            </form>
        </h2>


        <div class="my-container">

            <div id="res" class="row">
                <#list items as item>
                    <div class="col-sm-3">
                        <div class="card">
                            <div class="card-header" style="text-align: center">${item.getLabel()}</div>
                            <img style="margin-bottom:2rem; width: 100%; height: 100%;" src="${item.getPhotoPath()}">
                            <p class="card-text" style="text-align: center">${item.getDescription()}</p>
                            <p class="card-text" style="text-align: center"><strong>Price: ${item.getPrice()}$</strong>
                            </p>
                            <form name="my-form" action="removeFromCart" method="post">
                                <input type="hidden" name="id" value="${item.getId()}">
                                <button type="submit" class="btn btn-primary btn-block my-4">Remove from cart</button>
                            </form>
                        </div>
                    </div>
                </#list>
            </div>
            <#else>
                <h1 style="padding-left:7%;">No items in your cart yet</h1>
            </#if>

        </div>
    </div>

</@p.page>