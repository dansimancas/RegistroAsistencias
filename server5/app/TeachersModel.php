<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class TeachersModel extends Model
{
    //Se asigna la tabla Sirius_Teachers existente en la base de datos. Esta vista contiene la siguiente información:
    //ID (código del estudiante), PROGRAMA (programa en que esta el estudiante), STATUS(), TIPO(), COD_PROGRAMA(código del programa),
    //NOMBRES, APELLIDOS (nombres y apellidos del profesor, respectivamente), EMAIL (email del estudiante).

    protected $table = 'Sirius_Teachers';

}
