<#ftl encoding="UTF-8"/>
<#import "parts/base.ftl" as p/>
<@p.page>

    <script>
        function checkPass(x) {
            with (document)
                if (x)
                    getElementById('info').innerHTML = (getElementById('password').value !== getElementById('repeat').value) ?
                        '<span style="color: red"> not good!</span> <input type="hidden" name="check" value="no">' : '<span style="color: green" good!</span> <input type="hidden" name="check" value="yes">';
                else if (getElementById('repeat').value.length >= getElementById('password').value.length || getElementById('repeat').value.length < getElementById('password').value.length)
                    getElementById('info').innerHTML = (getElementById('password').value !== getElementById('repeat').value) ?
                        '<span style="color: red"> not good!.</span> <input type="hidden" name="check" value="no">' : '<span style="color: green"> good!</span><input type="hidden" name="check" value="yes">';
        }
    </script>

    <title>Registration</title>

    <div class="main_container">
        <div class="jumbotron" style="width:40%; background:none; margin: auto">
            <div class="container">
                <h1 class="display-4" style="padding-bottom:2rem;">Registration
                    <button type="submit" class="btn btn-link"><span> <a
                                    href="/login">You are already registered?</a></span></button>
                </h1>


                <form name="my-form" action="registration" method="post">


                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" id="name" name="name" placeholder="Type here"
                                   required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="lastName">Last name</label>
                            <input type="text" class="form-control" id="lastName" name="lastName"
                                   placeholder="Type here" required>
                        </div>
                    </div>


                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="login">Login</label>
                            <input type="text" class="form-control" id="login" name="login" placeholder="Type here"
                                   required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Type here"
                                   required>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Type here" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="repeat">Repeat password <b id="info"></b> </label>
                            <input type="password" class="form-control" id="repeat" name="repeat"
                                   placeholder="Type here" onchange="checkPass (1)" onkeyup="checkPass (0)"
                                   required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="address">Address</label>
                        <input type="text" class="form-control" id="address" name="address"
                               placeholder="Type here your full address" required>
                    </div>

                    <button id="signUpButton" class="btn btn-primary btn-block my-4" type="submit">Sign in</button>


                </form>


            </div>
        </div>
    </div>

</@p.page>