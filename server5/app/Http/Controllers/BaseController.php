<?php
/**
 * Created by PhpStorm.
 * User: daniela
 * Date: 20/03/15
 * Time: 11:23 AM
 */

namespace App\Http\Controllers;


class BaseController  extends Controller{

    protected function setupLayout()
    {
        if ( ! is_null($this->layout))
        {
            $this->layout = View::make($this->layout);
        }
    }

}