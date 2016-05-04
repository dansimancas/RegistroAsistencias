<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateAttendance extends Migration {

    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up() {
        Schema::create('attendance', function(Blueprint $table) {
            $table->increments('ID')->unsigned();
            $table->string('STUDENTID', 10);
            $table->string('NRC', 15);
            $table->integer('ATTENDANCE')->unsigned();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down() {
        Schema::drop('attendance');
    }

}
