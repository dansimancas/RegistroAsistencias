<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class CoursesByStudentModel extends Model {

    /**
     * Se asigna la vista de cursos por estudiante. Esta vista contiene la siguiente informacion:
     * STUDENTID (código del estudiante), SUBJECTNAME (nombre de la materia), NRC (código de la materia), SECTION (sección),
     * TEACHERID (código del profesor que dicta la materia), NAMES, LASTNAMES (nombres y apellidos del profesor, respectivamente)
     * nombre del profesor, número de créditos  y horas semanales.
     * @var string
     */

    protected $table = 'MATRICULA_EXTERNA_SAVIO';
    protected $connection = 'sirius_bd';

    public function course(){
        return $this->belongsTo('App\CoursesModel', 'IDNUMBER', 'NRC_PERIODO_KEY');
    }

    public function student(){
        return  $this->belongsTo('App\StudentsModel', 'USERNAME', 'ID');
    }

}
