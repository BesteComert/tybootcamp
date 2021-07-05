## Ön şart

Docker kurulumu işletim sistemine göre değişiyor. Docker'ı kurduktan sonra
`docker run --name postgresqldb -e POSTGRES_USER=myusername -e POSTGRES_PASSWORD=mypassword -p 5432:5432 -d postgres`
komutu ile docker içerisinde postgresql kurulumunu yapabilir;
`docker start postgresqldb`
komutu ile de db'yi başlatabilirsiniz. Bu komutlarla alakalı ayrıntılı bilgi için 
`https://hackernoon.com/how-to-install-postgresql-with-docker-quickly-ki7g363m makalesini inceleyebilirsiniz.`
Daha sonra kurduğumuz bu veri tabanına bağlanmamız gerekiyor, bunun için de DataGrip programını kullanacağız. DataGrip ücretli bir program ancak 30 günlük sürümü yetecektir. DataGrip ile tablo ekleme, tablolara veri ekleme, veriler üzerinde SQL dili ile sorgulama yapma özelliklerine kavuşacağız.
`DataGrip kurulumu için https://www.jetbrains.com/help/datagrip/installation-guide.html#standalone` 
adresindeki "Standalone Installation" adımını kullanabilirsiniz. Daha sonra DataGrip ile kurduğumuz PostgreSQL bağlantısını sağlamak için ise `https://www.jetbrains.com/help/datagrip/quick-start-with-datagrip.html#step-5-connect-to-a-database adresindeki` "Step 5. Connect to a database" maddesini uygulayabilirsiniz.

## Ödev

Öncelikle `spring-data-example` projesi altındakı projeyi refactor etmenizi ve sonrasında `Customer` entitysinin implementation'unu tamamlamanızı bekliyoruz. 
Sonrasinda ise bu customer'a sepete ürün ekleyebilme yetkinlikiği kazandırmanızı bekliyoruz. Nice to have olarak sipariş yetkinliği de kazandırabilirsiniz.

Erd:
![](erd.jpeg)

Sunum: https://docs.google.com/presentation/d/1znxeXDMDwezcsPY7_34oFAD5jWS2bBKS_TBN_PVGs8k/edit?usp=sharing
