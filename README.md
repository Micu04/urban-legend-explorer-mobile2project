# Urban Legend Explorer

## Projekt leírás

Az **Urban Legend Explorer** egy Android alkalmazás, amely városi legendák és paranormális történetek böngészésére és kezelésére szolgál.  
A felhasználó megtekintheti a legendák listáját, részletes információkat olvashat róluk, valamint új legendákat hozhat létre, módosíthat vagy törölhet.

Az alkalmazás Java nyelven készül Android Studio fejlesztőkörnyezetben.  
A listák megjelenítésére RecyclerView, az adatok tárolására Room adatbázis kerül használatra.

---

## Fő funkciók

- városi legendák listázása
- legenda részletes megjelenítése
- új legenda létrehozása
- legenda módosítása
- legenda törlése
- keresés legendák között
- rendezés különböző szempontok szerint
- képek megjelenítése Glide segítségével

---

## Felhasznált technológiák

- Android Studio  
- Java  
- Android SDK  
- RecyclerView  
- ConstraintLayout  
- Fragment alapú felépítés  
- Room adatbázis  
- Glide  
- Git verziókezelés  
- GitHub repository

---

## Projekt szerkezete

Az alkalmazás **egy Activity és több Fragment** segítségével valósul meg.

Főbb képernyők:

- **LegendListFragment** – legendák listázása, keresés és rendezés  
- **LegendDetailFragment** – egy legenda részletes adatainak megjelenítése  
- **AddEditLegendFragment** – új legenda létrehozása és meglévő szerkesztése

---

## Csapattagok és feladatmegosztás

### Janik Miklós Balázs
- projekt struktúra kialakítása
- az alkalmazás felhasználói felületének és designjának megtervezése
- navigáció és fragment alapú felépítés kialakítása
- legendák listájának megjelenítése RecyclerView segítségével
- keresési és rendezési funkciók implementálása

### Ölvödi Soma
- Room adatbázis implementálása
- legenda részletes nézet elkészítése
- új legenda létrehozása
- meglévő legenda módosítása
- legenda törlése
- képek betöltése Glide segítségével
