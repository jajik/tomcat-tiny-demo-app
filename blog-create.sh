read -p "Owner id:" OWNER
read -p "Blog Post title:" TITLE
read -p "Blog post content:" TEXT

TITLE_ENC=$(echo $TITLE | sed -e 's/ /%20/g')
TEXT_ENC=$(echo $TEXT   | sed -e 's/ /%20/g')

curl -X POST -H "Content-Type: text/plain" "http://localhost:8080/app/blog?owner=$OWNER&name=$TITLE_ENC&text=$TEXT_ENC"
