echo "Compiling sources..."

parent_dir=$(pwd)

for pkgs in app/WEB-INF/src/com/*; do
	dest=$(echo $pkgs | sed -e "s/src/classes/")
    if [[ ! -d $dest ]]; then
        mkdir -p $dest
    fi
    javac -cp /usr/share/java/tomcat-servlet-api.jar:app/WEB-INF/src/ $pkgs/*.java
    mv $pkgs/*.class $parent_dir/$dest
done

echo "Done"
echo "Creating the .war app..."
cd app/
zip -r ../app.war *
cd ..
echo "    Done"