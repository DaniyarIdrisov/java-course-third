<#ftl encoding="UTF-8"/>
<#import "parts/base.ftl" as p/>
<@p.page>
    <title>Profile</title>


    <div class="main_container">
        <h1 style="text-align:center; margin-bottom: 2rem;">Your profile <span> <form name="my-form"
                                                                                      action="logout"
                                                                                      method="post"><button
                            type="submit" class="btn btn-link"><span>Logout</span></button></form></span></h1>

        <div class="my-container" style="width:64%; margin: auto;">
            <div class="row">
                <div class="col-sm-6">
                    <div class="card">
                        <div class="card-header">Information about you:</div>
                        <div class="card-body">
                            <h5 class="card-title">Name: ${user.getName()} &nbsp; ${user.getLastName()}</h5>
                            <p class="card-text">Email: ${user.getEmail()}</p>
                            <p class="card-text">Login: ${user.getLogin()}</p>
                            <p class="card-text">Address: ${user.getAddress()}</p>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="card">
                        <div class="card-header">Your orders:</div>

                        <#if orders?has_content>
                            <#list orders as order>
                                <div class="card">
                                    <div class="card-header">Item: ${order.getItemName()}</div>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">Order number: ${order.getId()}</li>
                                        <li class="list-group-item">Order prise: ${order.getPrice()}$</li>
                                    </ul>
                                </div>
                            </#list>
                        <#else>
                            <div class="card" style="text-align: center">
                                <div class="card-header">No orders yet</div>
                                <a href="/catalog" class="btn btn-primary">Go shopping!</a>
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@p.page>