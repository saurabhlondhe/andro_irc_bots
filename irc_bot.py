import platform
import random
import socket
import sys

reload(sys)
sys.setdefaultencoding('utf8')


channel = sys.argv[1]
server = sys.argv[2]
botnick = sys.argv[4]
sentUser = False
sentNick = False
hi_greet=["hi","hi!","hey","hey!","hello","hello!"]
irc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print "\nConnecting to:" + server
irc.connect((server, int(sys.argv[3])))
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
         irc.send("USER " + botnick + " " + botnick + " " + botnick + " :"+sys.argv[5]+"\n")
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
