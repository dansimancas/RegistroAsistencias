<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class StudentsByCourseModel extends Model {

    //Se asigna la vista de StudentsByCourse existente en la base de datos. Esta vista contiene la siguiente información:
    //TEACHERID (código del profesor), SUBJECT (nombre de la materia), NRC (código de la materia, año y periodo),
    //STUDENTID(código del estudiante),NAMES, LASTNAMES (nombres y apellidos del estudiante, respectivamente),
    //PROGRAM(programa del estudiante), EMAIL(email del estudiante).

    protected $table = 'StudentsByCourse';

}
