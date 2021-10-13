import os
def searchContainerbyName(containerName):
    a = os.popen('docker ps -all --filter "name={}"'.format(containerName)).readlines()
    a.pop(0)
    return len(a)>0
#Runs rabbitmodule
if (searchContainerbyName("rabbitmodule")):
    os.system('docker start rabbitmodule')
else:
    os.system('docker run -d --hostname my-rabbit --name rabbitmodule -p 8085:15672 -p 8086:5672 rabbitmq:3-management')

