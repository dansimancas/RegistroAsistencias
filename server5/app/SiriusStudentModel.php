<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class SiriusStudentModel extends Model {

    //Se asigna la tabla de cursos para trabajar con el SiriusStudentModel. En este caso la tabla de estudiantes
    // que contiene información sobre los estudiantes: su T000, nombres, apellidos, programa, status, tipo, código
    // del programa y el email.
    protected $table = 'SIRIUS_STUDENTS';

}
