curl -XDELETE http://qa.goglides.com:9200/goglides_qa
curl -XPUT http://qa.goglides.com:9200/goglides_qa -d @setup.json
#curl -s -XPOST http://qa.goglides.com:9200/_bulk --data-binary @goglides_dev_test_bulk.json
#!/bin/bash
# init
function pause(){
   read -p "$*"
}
 
# ...
# call it
pause 'Press [Enter] key to continue...'