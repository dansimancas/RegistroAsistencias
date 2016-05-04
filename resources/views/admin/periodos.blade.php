<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title>Administrar Periodos</title>
        <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <h2>Períodos</h2>
                    <a href="/admin/periodos/create" class="btn btn-primary">Agregar</a>
                    <br><br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Periodo</th>
                                <th>Activo</th>
                                <th>Funciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach($periodos as $periodo)
                            <tr>
                                <th>
                                    {{$periodo->periodo}}
                                </th>
                                <th>
                                    {{$periodo->active}}
                                </th>
                                <th>
                                    <a href="periodos/{{$periodo->id}}/edit">Editar</a>
                                </th>
                            </tr>
                            @endforeach
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="/js/jquery.min.js"></script>
        <script src="/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
