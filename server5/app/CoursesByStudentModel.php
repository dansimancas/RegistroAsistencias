<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class CoursesByStudentModel extends Model {

<<<<<<< HEAD
    //Se asigna la vista de CoursesByStudent existente en la base de datos. Esta vista contiene la siguiente información:
    //STUDENTID (código del estudiante), SUBJECTNAME (nombre de la materia), NRC (código de la materia), SECTION (sección),
    //TEACHERID (código del profesor que dicta la materia), NAMES, LASTNAMES (nombres y apellidos del profesor, respectivamente).
=======
    /**
     * Se asigna la vista de cursos por estudiante. Esta vista contiene la siguiente informacion:
     * STUDENTID (código del estudiante), SUBJECTNAME (nombre de la materia), NRC (código de la materia), SECTION (sección),
     * TEACHERID (código del profesor que dicta la materia), NAMES, LASTNAMES (nombres y apellidos del profesor, respectivamente)
     * nombre del profesor, número de créditos  y horas semanales.
     * @var string
     */
>>>>>>> origin/master

    protected $table = 'CoursesByStudent';

}
