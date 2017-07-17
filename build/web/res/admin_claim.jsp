<script type="text/javascript">
    var claim, agent;
    function findAgent() {
        var nic = $('#agentNIC').val();
        if (nic) {
            $.post('GetAgent', {nic: nic}, function(data, textStatus, jqXHR) {
                if (textStatus === 'success') {
                    if (data !== 'NA') {
                        agent = nic;
                        $('#agentName').html(data);
                    } else {
                        agent = null;
                    }
                }
            }, 'html');
        } else {
            alertify.alert('Please Enter NIC.');
        }
    }
    function assignAgent() {
        if (agent !== null) {
            $.post('AssignAgent', {claim: claim, agent: agent}, function(data, textStatus, jqXHR) {
                if (textStatus === 'success') {
                    if (data === '1') {
                        alertify.success('Successfully Assigned !');
                        agent = null;
                        claim = null;
                        document.location.reload();
                    } else {
                        alertify.error('Something went wrong !');
                    }
                }
            }, 'text');
        } else {
            alertify.alert('Please select agent.');
        }
    }
    function assignAgentModal(id) {
        claim = id;
        $('#assignModal').modal();
    }
    function viewLocation(lng, lat) {
        $('#locationModal').modal();

        $('#locationModal').on('shown.bs.modal', function() {
            // google.maps.event.trigger(map, "resize");
            var myLatLng = new google.maps.LatLng(lat, lng);
            console.log(myLatLng);
            var mapOptions = {
                zoom: 17,
                center: myLatLng
            };
            var map = new google.maps.Map(document.getElementById('temp-map'), mapOptions);
            var mapMarker = new google.maps.Marker({
                position: myLatLng,
                map: map,
                animation: google.maps.Animation.BOUNCE
            });
            var info = new google.maps.InfoWindow({
                content: 'Claim Location'
            });
            google.maps.event.addListener(mapMarker, 'click', function() {
                info.open(map, mapMarker);
            });
            info.open(map, mapMarker);
            google.maps.event.trigger(map, "resize");
        });
    }
    function moreInfo(id) {
        $('#more-info').modal();
        $.post('GetClaim', {id: id}, function(data, textStatus, jqXHR) {
            if (textStatus === 'success') {
                console.log(data[0]);
                var dateTime = data[0].claim.date_time;
                $("#date-time").html(dateTime);

                //<editor-fold defaultstate="collapsed" desc="Damage Type">
                var damage = data[0].claim.damage;

                if (damage === 'High') {
                    damage = '<a class="btn btn-danger">High Damage</>';
                } else if (damage === 'Medium') {
                    damage = '<a class="btn btn-warning">Medium Damage</>';
                } else if (damage === 'Low') {
                    damage = '<a class="btn btn-success">Low Damage</>';
                }

                $('#damage-type').html(damage);
                //</editor-fold>

                var description = data[0].claim.description;
                $('#description').html(description);

                var vehicle = data[0].claim.vehicle;

                var veh = "<b>" + vehicle.id + "</b> " + vehicle.brand + " " + vehicle.model + " " + vehicle.year + "<small>(" + vehicle.type + ")</small>";
                $('#claimVehicle').html(veh);

                $('#vehicleColor').html(vehicle.color);
                $('#chasisNo').html(vehicle.chasis_no);
                $('#engineNo').html(vehicle.engin_no);

                var coverage = vehicle.coverage;

                $('#insuraceType').html(coverage.type);
                $('#insuredDate').html(coverage.insured_date);
                $('#expireDate').html(coverage.expire_date);
                $('#period').html(coverage.period + " " + coverage.period_type);

                var owner = vehicle.owner;

                $('#owner').html(owner.fname + " " + owner.lname + "<small>(" + owner.nic + ")</small>");
                $('#primaryMobile').html(owner.mobile);

            }
        }, 'json');
    }
    function searchClaims() {
        $('#claims').html(getLoader());
        $.post('GetClaims', {},
                function(data, textStatus, jqXHR) {
                    if (textStatus === 'success') {
                        var html = '';
                        for (var i = 0; i < data[0].claims.length; i++) {
                            html += '<div style="margin: 5px 15px;" class="row well well-sm">'
                                    + '<div class="col-sm-3">'
//                                    + '<a href="#" class="thumbnail">'
                                    + '<video id="VID_' + data[0].claims[i].id + '" controls="true" style="max-width: 250px;" src="' + data[0].claims[i].evidence + '"></video>'
//                                    + '</a>'
                                    + '</div>'
                                    + '<div class="col-sm-8">'
                                    + '<h3>' + data[0].claims[i].vehicle_brand + ' ' + data[0].claims[i].vehicle_model + ' ' + data[0].claims[i].vehicle_year + '<br><small>' + data[0].claims[i].owner + '</small></h3>'
                                    + '<div class="btn-group pull-right" role="group">'
                                    + '<button title="Claim Location" class="btn btn-info action-map" onclick="viewLocation(\'' + data[0].claims[i].longitude + '\',\'' + data[0].claims[i].latitude + '\')"><span class="glyphicon glyphicon-map-marker"></span></button>'
//                                    + '<button title="Owner Details" class="btn btn-info action-owner"  owner="' + data[0].claims[i].owner + '"><span class="glyphicon glyphicon-user"></span></button>'
                                    + '<button onclick="moreInfo(\'' + data[0].claims[i].id + '\')" title="More Details" class="btn btn-success action-more" ><span class="glyphicon glyphicon-option-horizontal"></span></button>'
                                    + '</div>';
                            if (data[0].claims[i].agent === 'NA') {
                                html += '<button class="btn btn-primary" onclick="assignAgentModal(\'' + data[0].claims[i].id + '\')">Assign Agent</button>';
                            }
                            html += '<p>' + data[0].claims[i].description + '</p>'
                                    + '</div>'
                                    + '<div class="col-sm-1">'
                                    + '<div style="">'
                                    + '<label style="" class="label label-gray">' + data[0].claims[i].date + ' </label>'
                                    + '</div>'
                                    + '</div>'
                                    + '</div>';
                        }
                        $('#claims').html(html);
                    }
                }, 'json');
    }
    $(document).ready(function() {
        searchClaims();

//        setInterval(searchClaims, 5000);

    });
</script>
<div class="panel panel-default">
    <div class="panel-heading">
        <h2 class="panel-title">Reported Claims</h2>
    </div>
    <div class="panel-body">
        <!--        <div class="well well-sm">
                    <form class="form-inline">
                        <div class="form-group">
                            <label>Claim Status:</label>
                            <select class="form-control">
                                <option value="1">Claimed</option>
                                <option value="2">In Progress</option>
                                <option value="3">Rejected</option>
                                <option value="5">Completed</option>
                            </select>
                        </div>
                    </form>
                </div>-->
        <div id="claims">
        </div>
    </div>
</div>

<div class="modal fade" id="more-info">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">More info...</h4>
            </div>
            <div class="modal-body" id="claimBody">
                <div class="well well-sm">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div id="damage-type"></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3">Date & Time:</label>
                            <div class="col-sm-9">
                                <p id="date-time" class="form-control-static">--/--/-- --:--:--</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3">Description:</label>
                            <div class="col-sm-9">
                                <p id="description" class="form-control-static">N/A</p>
                            </div>
                        </div>
                    </div>
                </div>
                <!--WELL-->
                <div>
                    <legend id="claimVehicle">Vehicle Name</legend>
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-sm-3">Vehicle Color</label>
                            <div class="col-sm-9">
                                <p id="vehicleColor" class="form-control-static">N/A</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3">Engine No</label>
                            <div class="col-sm-9">
                                <p id="engineNo" class="form-control-static">N/A</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3">Chasis No</label>
                            <div class="col-sm-9">
                                <p id="chasisNo" class="form-control-static">N/A</p>
                            </div>
                        </div>
                    </div>
                </div>
                <!--VEHICLE-->
                <div>
                    <legend id="insuraceType">Insurance</legend>
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-sm-3">Insured Date</label>
                            <div class="col-sm-9">
                                <p id="insuredDate" class="form-control-static">N/A</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3">Expire Date</label>
                            <div class="col-sm-9">
                                <p id="expireDate" class="form-control-static">N/A</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3">Period of Cover</label>
                            <div class="col-sm-9">
                                <p id="period" class="form-control-static">N/A</p>
                            </div>
                        </div>
                    </div>
                </div>
                <!--OWNER-->
                <div>
                    <legend id="owner">Owner</legend>
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-sm-3">Primary Mobile</label>
                            <div class="col-sm-9">
                                <p id="primaryMobile" class="form-control-static">N/A</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!--MORE INFO MODAL-->
<div class="modal fade" id="locationModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Location info...</h4>
            </div>
            <div class="modal-body">
                <div id="temp-map" style="height: 400px"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!--LOCATION MODAL-->
<div class="modal fade" id="assignModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Assign Agent...</h4>
            </div>
            <div class="modal-body">
                <div class="form-inline">
                    <div class="form-group">
                        <input id="agentNIC" name="nic" onkeyup="findAgent()" placeholder="999999999V" type="text" class="form-control form-group-lg">
                    </div>
                    <div class="form-group">
                        <button type="button" onclick="findAgent()" class="btn btn-success">Find</button>
                    </div>
                </div>
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-3">Full Name</label>
                        <div class="col-sm-9">
                            <p id="agentName" class="form-control-static">N/A</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button class="btn btn-primary" onclick="assignAgent()">Assign Agent</button>
            </div>
        </div>
    </div>
</div>
