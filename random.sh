
CURL_LIST="
http://localhost:8080/call.pinpoint
http://localhost:8080/getGeoCode.pinpoint
http://localhost:8080/getTwitterUrlCount.pinpoint
http://localhost:8080/getTwitterUrlCountByPost.pinpoint
http://localhost:8080deleteUser.pinpoint
http://localhost:8080/getUser.pinpoint
http://localhost:8080/insertUser.pinpoint
http://localhost:8080/updateUser.pinpoint
http://localhost:8080/hello.pinpoint
"

while [ 1 ]
do

	for CURL in $CURL_LIST;
	do
		value=`expr $RANDOM % 2`
		if [ $value -ne 0 ]; then
        		curl --silent -o /dev/null $CURL
		fi		
	
	done	

        sleep $1
done

