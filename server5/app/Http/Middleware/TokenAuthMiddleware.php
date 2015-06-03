<?php namespace App\Http\Middleware;

use Closure;
use Request;
use App\TokenModel;

class TokenAuthMiddleware {

	/**
	 * Handle an incoming request.
	 *
	 * @param  \Illuminate\Http\Request  $request
	 * @param  \Closure  $next
	 * @return mixed
	 */
	public function handle($request, Closure $next)
	{

        /*Validar si el token corresponde al usuario que pide la info*/
        $username = Request::input('username');
        $token = Request::input('token');

        //Comprobación...
        $data = TokenModel::where("USERNAME", "=", $username)
                ->Where("TOKEN", "=", $token)
                ->get();

        if($data == null){

        }else{

        }

		return $next($request);
	}

}
