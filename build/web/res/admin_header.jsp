<nav class="navbar navbar-default" style="border-radius: 0">
    <div class="container-fluid">
        <div class="navbar-header">
            <div class="navbar-brand">
                <img alt="Brand" class="img-responsive" style="max-height: 24px;max-width: 24px" src="img/icon.png">
            </div>
            <a href="admin_panel.jsp" class="navbar-brand">InsurePlus+</a>
        </div>
        <ul class="nav navbar-nav navbar-left">
            <!--<li class="active"><a href="#overview" onclick="getOverview()">Overview</a></li>-->
            <li><a href="#" onclick="getClaims()">Insurance Claim</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Agent <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a onclick="registerAgent()" href="#">Register an Agent</a></li>
                    <li><a onclick="getAgents()" href="#">View Agent</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Owner <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a onclick="loadContent('res/register_owner.jsp')" href="#">Register new Owner</a></li>
                    <li><a onclick="loadContent('res/view_owner.jsp')" href="#">View Registered Owner</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Vehicle <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a onclick="loadContent('res/register_vehicle.jsp')" href="#">Insure new Vehicle</a></li>
                    <li><a onclick="loadContent('res/view_vehicle.jsp')" href="#">View Insured Vehicle</a></li>
                </ul>
            </li>
        </ul>
        <ul class="navbar-form navbar-right">
            <button id="sign-out-btn" onclick="signOut()" title="Sign out" class="btn btn-default"><span class="glyphicon glyphicon-off"></span></button>
        </ul>
    </div>

</nav>