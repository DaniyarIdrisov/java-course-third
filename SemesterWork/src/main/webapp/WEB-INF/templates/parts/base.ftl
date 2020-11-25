<#ftl encoding="UTF-8"/>
<#macro page>
    <html lang="en">
    <head>

        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
              crossorigin="anonymous">
        <link rel="stylesheet" href="/css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    </head>

    <body style="background:url(/images/style/background.jpg) no-repeat center top / cover;">
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <!-- Brand/logo -->
        <a class="navbar-brand" href="/main"><img src="/images/style/logo.png" width="40" height="40"> Dimars Football
            Team Store</a>

        <!-- Links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/catalog">Catalog</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile">Your profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/registration">Registration</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/cart">Cart</a>
            </li>
        </ul>
    </nav>
    <#nested>

    </body>
    </html>
</#macro>