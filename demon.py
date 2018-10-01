import socket,os               # Import socket module
soc = socket.socket()         # Create a socket object
host = "" # Get local machine name
port = 2004                # Reserve a port for your service.
soc.bind((host, port))       # Bind to the port
soc.listen(5)
while True:
    conn, addr = soc.accept()     # Establish connection with client.
    print ("Got connection from",addr)
    msg = conn.recv(1024).decode("utf-8")
    msg=msg.replace("\n",":")
    msg = msg.split(":")
    a=[]
    for i in range(1,len(msg),2):
        a.append(msg[i].strip())
    os.system("python2 irc_bot.py '"+a[0]+"' "+a[1]+" "+a[2]+" "+a[3]+" "+a[4]+" &")
    print (a)
    print("---------------------------")

