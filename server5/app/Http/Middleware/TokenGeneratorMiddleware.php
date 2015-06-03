<?php namespace App\Http\Middleware;

use Closure;
use Request;
use App\TokenModel;

class TokenGeneratorMiddleware {

	/**
	 * Handle an incoming request.
	 *
	 * @param  \Illuminate\Http\Request  $request
	 * @param  \Closure  $next
	 * @return mixed
	 */
	public function handle($request, Closure $next)
	{

        //@TODO: Falta sanear completamente el username
        $username = Request::input('username');;
        $rdn  = ',ou=all,ou=people,dc=utbvirtual,dc=edu,dc=co';
        $password = Request::input('password');
        $hostname = '23.253.34.120';

        if ( !$username or !$password) {
            return response('Datos de acceso faltantes.', 401);
        }

        if (!extension_loaded('ldap')) {
            return response('PHP LDAP extension not loaded.', 418);
        }

        if (! $conn = ldap_connect("ldap://$hostname")) {
            return response("Could not connect to LDAP host $hostname: " . ldap_error($conn),401);
        }

        if (!@ldap_bind($conn, "uid=".$username.$rdn, $password )) {
            return response('Could not bind to AD: ' . ldap_error($conn),401);
        }else{
            $token = TokenModel::where("username", "=", $username)->first();
            if($token == null){
                $token = new TokenModel;
                $token->USERNAME= $username;
                $token->TOKEN= csrf_token();
                $token->save();
            }else{
                $token->token = csrf_token();
                $token->save();
            }
            return $next($request);
        }

	}

}
