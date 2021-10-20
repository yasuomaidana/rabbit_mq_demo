import os
def searchContainerbyName(containerName):
    a = os.popen('docker ps -all --filter "name={}"'.format(containerName)).readlines()
    a.pop(0)
    return len(a)>0
def runByName(name):
    os.system('docker start {}'.format(name))

#Runs rabbitmodule
if (searchContainerbyName("rabbitmodule")):
    runByName("rabbitmodule")
else:
    os.system('docker run -d --hostname my-rabbit --name rabbitmodule -p 8085:15672 -p 8086:5672 rabbitmq:3-management')

#Runs mysqlmodule
if (searchContainerbyName('databasedemo')):
    runByName("databasedemo")
else:
    os.system('docker run --name databasedemo -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -p 3306:3306 -d mysql')