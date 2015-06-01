<?php namespace App\Http\Middleware;

use Closure;

class TokenGenerateMiddleware {

	/**
	 * Handle an incoming request.
	 *
	 * @param  \Illuminate\Http\Request  $request
	 * @param  \Closure  $next
	 * @return mixed
	 */
	public function handle($request, Closure $next)
	{

        // using ldap bind
        $ldaprdn  = 'uid=T0000000,ou=all,ou=people,dc=utbvirtual,dc=edu,dc=co';
        $ldappass = '';  // associated password
        $hostname = '23.253.34.120';

        if (! extension_loaded('ldap')) {
            return response('PHP LDAP extension not loaded.', 401);
        }
        if (! $conn = ldap_connect("ldap://$hostname")) {
            return response("Could not connect to LDAP host $hostname: " . ldap_error($conn),401);
        }
        //ldap_set_option($conn, LDAP_OPT_REFERRALS, 0);

        if (!@ldap_bind($conn, $ldaprdn, $ldappass )) {
            return response('Could not bind to AD: ' . ldap_error($conn),401);
        }else{
            return $next($request);
        }

	}

}
