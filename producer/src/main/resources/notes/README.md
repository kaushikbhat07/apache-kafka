## Fix for unable to connect to kafka broker:

1)

I ran the following command in my WSL2 Ubuntu shell: ip addr | grep "eth0" I made note of the ip address against the
inet property, for example, 172.27.10.68

In my Kafka server.properties I replaced the listeners property value as follows: listeners=PLAINTEXT://172.27.10.68:
9092 I commented out the advertised.listeners property. But you can alternatively assign the ip in question to this
property, and have the listeners property set to 0.0.0.0. But I assume you are using the Kafka installation for
testing/learning purposes, so I would keep it simple.

I made no change to the Zookeeper's default ip:port

I am using the Schema Registry, so I modified the Kafka bootstrap property as follows:
kafkastore.bootstrap.servers=PLAINTEXT://172.27.10.68:9092 I made no change to the default schema registry listener
listeners=http://0.0.0.0:8081

I used the same ip (as listed above) in my IntelliJ Kafka Producer. It then happily connected to my Kafka broker in
WSL2.

2)

Had this same issue. The root cause seems to be that WSL2 is broken with regards to IPv6 and localhost (
See: https://github.com/microsoft/WSL/issues/4851)

The only fix I found that doesn't involve changing configs every time you reboot (per the "172.*" suggestion above) is
to use the IPv6 loopback address ::1 in both the Kafka server config running in Linux and the Java client in Windows.

In server.properties I have this:

listeners=PLAINTEXT://[::1]:9092
And likewise in my Java client bootstrap server config I use

"[::1]:9092"

https://stackoverflow.com/questions/64177422/unable-to-produce-to-kafka-topic-that-is-running-on-wsl-2-from-windows/65553634#65553634