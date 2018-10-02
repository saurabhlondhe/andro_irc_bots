import socket,os,thread
soc = socket.socket()
host = ""
port = 2004
soc.bind((host, port))
soc.listen(5)

def hello_bot(CHAN,SERVER,PORT,NICK,NAME):
    import platform
    import random
    import socket
    import sys

    reload(sys)
    sys.setdefaultencoding('utf8')


    channel = CHAN
    server = str(SERVER)
    botnick = NICK
    sentUser = False
    sentNick = False
    hi_greet=["hi","hi!","hey","hey!","hello","hello!"]
    irc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    print "\nConnecting to:" + server
    irc.connect((server, int(PORT)))
    print ("done")

    try:
        while 1:
            text = irc.recv(2048)
            if len(text) > 0:
                print text
            else:
                continue
            msg=text.lower().split(":")[-1]
            user_name=text.split("!")[0][1:]
            print ("name:"+user_name)
            if msg.strip() in hi_greet:
                print("send pong")
                irc.send("PRIVMSG "+channel+" :"+user_name+", "+hi_greet[random.randint(0, 5)]+" \n")
                print("success")

            if text.find("PING") != -1:
                irc.send("PONG " + text.split()[1] + "\n")
                print("in ping")

            if sentUser == False:
                irc.send("USER " + botnick + " " + botnick + " " + botnick + " :"+NAME+"\n")
                sentUser = True
                print("set user name")
                continue

            if sentUser and sentNick == False:
                irc.send("NICK " + botnick + "\n")
                sentNick = True
                print("nick")
                continue

            if text.find("255 " + botnick) != -1:
                irc.send("JOIN " + channel + "\n")

            if text.find(":!host") != -1:
                irc.send("PRIVMSG " + channel + " :" + str(platform.platform()) + "\n")

    except KeyboardInterrupt:
        irc.send("QUIT :I have to go for now!\n")
        print "\n"
        sys.exit()




while True:
    conn, addr = soc.accept()     # Establish connection with client.
    print ("Got connection from",addr)
    msg = conn.recv(1024)
    msg=msg.replace("\n",":")
    msg = msg.split(":")
    a=[]
    for i in range(1,len(msg),2):
        a.append(msg[i].strip())
    thread.start_new_thread(hello_bot,(a[0],a[1],a[2],a[3],a[4]))
    print (a)
    print("---------------------------")

