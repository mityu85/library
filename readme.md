#Könyvtár
***
Az alkalmazás alap könyvtári funkciókat valósít meg. Segítségével adatbázisban lehet tárolni a kölcsönözhető könyveket
és nyomon követni az olvasók listáját. MariaDB adatbázis szolgáltatja a perzisztens réteget. Az üzleti logika Spring
Boot megvalósításával jött létre.

##Táblák
***
###Olvasó
* Név
* Cím
* Könyv lista

###Könyv
***
* Szerző
* Cím
* Kölcsönzés dátuma
* Olvasó
##Funkciók
***
* Mentés
* Lekérdezés (ID alapján vagy teljes lista)
* Módosítás
* Törlés (ID alapján vagy teljes lista)