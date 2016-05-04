echo "Descargando actualizaciones de GitHub"
git pull
echo "Cambiando permisos para apache"
sudo chown -R www-data:www-data ../../RegistroAsistencias/
sudo chmod -R ug+rw ../../RegistroAsistencias/
echo "Done."

