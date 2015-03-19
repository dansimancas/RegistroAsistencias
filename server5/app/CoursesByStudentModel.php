<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class CoursesByStudentModel extends Model {

    //Se asigna la vista de CoursesByStudent existente en la base de datos. Esta vista contiene la siguiente información:
    //STUDENTID (código del estudiante), SUBJECTNAME (nombre de la materia), NRC (código de la materia), SECTION (sección),
    //TEACHERID (código del profesor que dicta la materia), NAMES, LASTNAMES (nombres y apellidos del profesor, respectivamente).

    protected $table = 'CoursesByStudent';

}
