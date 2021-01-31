--liquibase formatted sql

--changeset author:schulewa

--comment list of language families

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Ababuan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Afro-Asiatic', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa, Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Ainu', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Alacalufan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Algic', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Algonquian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Araucanian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Arawakan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Arawan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Arnhem Land', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Arutani-Sape', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Austroasiatic', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Austronesian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa, Asia, Oceania';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Aymaran', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Baining', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Barbacoan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Berta', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Border', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Bunuban', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Caddoan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Cahuapanan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Carib', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Catacaoan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Central Solomon', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Chapacuran', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Charruan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Chibchan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Chimakuan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Chimuan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Choco', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Chonan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Chukotko-Kamchatkan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Constructed', null, 'N', 'admin', 0;

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Creole', null, 'N', 'admin', 0;

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Dené–Yeniseian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Digaro', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Dravidian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'East Bird\'s Head - Sentani', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'East Geelvink Bay', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Eastern Trans-Fly', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Eskimo-Aleut', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Esmeralda-Yaruroan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Fas', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Guaicuruan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Gunwinyguan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Hibito-Cholon', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Hmong-Mien', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Hokan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Indo-European', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia, Europe';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'International Auxiliary', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'World ?';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Iroquoian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Japonic', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Jarrakan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Jicaquean', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Jirajaran', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Jivaroan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Kadu', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Kartvelian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia, Europe';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Katembri-Taruma', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Katukinan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Keres', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Kho-Bwa', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Khoe', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Khoman', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Koreanic', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Kra-Dai', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Kuliak', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Kwomtari', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Kx\'a', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Lakes Plain', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Left May', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Lencan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Limilngan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Lule-Vilela', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Macro-Jê', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Macro-Otomákoan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Mairasi', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Mande', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Mascoian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Matacoan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Mayan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Mirndi', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Misumalpan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Mixe-Zoque', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Mongolic', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Mosetenan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Mura', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Muskogean', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Na-Dene', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Nadahup', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Nambikwaran', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Niger-Congo', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Nilo-Saharan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Nimboran', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Nivkh', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'North Bougainville', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Northeast Caucasian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia, Europe';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Northwest Caucasian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Caucasian';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Nyulnyulan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Ongan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Oto-Manguean', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Otomákoan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Paezan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Pama-Nyungan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Paman', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Pano-Tacanan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Peba-Yaguan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Penutian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Piaroa-Saliban', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Piawi', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Puinavean', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Quechuan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Ramu - Lower Sepik', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Salishan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Senagi', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Sepik', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Siangic', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Sino-Tibetan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Siouan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Skou', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Songhay', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'South Bougainville', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Southern Daly', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Tai-Kadai', null, 'N', 'admin', 0;

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Takic', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Tangikic', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Tanoan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Tequiraca-Canichana', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Timotean', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Tiniguan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Tor-Kwerba', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Torricelli', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Totonacan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Trans-Fly - Bulaka River', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Trans-Fly-New Guinea', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Tucanoan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Tungusic', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Tupian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Turkian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia, Europe';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Turkic', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia, Europe';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Tuu', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Ubangian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Uralic', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Uru-Chipaya', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Uto-Aztecan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Wagaydyic', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Wakashan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'West New Britain', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'West Papuan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Western Daly', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Wintuan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Witotoan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Worrorran', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Xincan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Yanomaman', ID, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Yeniseian', ID, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Yok-Utian', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Yuat', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Yukaghir', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Yuki-Wappo', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by, version_no)
SELECT 'Zamucoan', id, 'N', 'admin', 0 FROM resdb_region WHERE name = 'South America';

