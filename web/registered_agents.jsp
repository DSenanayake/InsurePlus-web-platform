<script type="text/javascript">
    function searchAgents() {
        $('#agents').html(getLoader());
        var keyword = $('#search').val();
        $.post('getAgents', {search: keyword},
        function(data, textStatus, jqXHR) {
            if (textStatus === 'success') {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    html += '<div style="margin: 5px 15px;" class="row well well-sm">'
                            + '<div class="col-sm-2">'
                            + '<a href="#" class="thumbnail">'
                            + '<img class="img-thumbnail" style="max-height:128px;" src="' + data[i].profile + '">'
                            + '</a>'
                            + '</div>'
                            + '<div class="col-sm-9">'
                            + '<h3>' + data[i].fname + ' ' + data[i].lname + '<br><small>' + data[i].nic + '</small></h3>'
                            + '<button class="btn btn-info" agent="' + data[i].nic + '"><span class="glyphicon glyphicon-earphone"></span></button>'
                            + '</div>'
                            + '<div class="col-sm-1">'
                            + '<div style="">';
                    if (data[i].status === 'Online') {
                        html += '<label class="label label-success">Available</label>';
                    } else if (data[i].status === 'Assigned') {
                        html += '<label class="label label-warning">Assigned</label>';
                    } else {
                        html += '<label class="label label-danger">Disabled</label>';
                    }
                    html += '</div>'
                            + '</div>'
                            + '</div>';
                }
                $('#agents').html(html);
            }
        }, 'json');
    }
    $(document).ready(function() {
        searchAgents();
    });
</script>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Registered Agents</h3>
    </div>
    <div class="panel-body">
        <div id="agents" ></div>
    </div>
</div>