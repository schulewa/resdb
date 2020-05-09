--liquibase formatted sql

--changeset author:schulewa

--comment list of language families

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Ababuan', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Afro-Asiatic', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa, Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Ainu', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Alacalufan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Algic', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Algonquian', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Araucanian', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Arawakan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Arawan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Arnhem Land', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Arutani-Sape', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Austroasiatic', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Austronesian', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa, Asia, Oceania';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Aymaran', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Baining', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Barbacoan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Berta', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Border', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Bunuban', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Caddoan', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Cahuapanan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Carib', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Catacaoan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Central Solomon', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Chapacuran', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Charruan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Chibchan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Chimakuan', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Chimuan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Choco', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Chonan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Chukotko-Kamchatkan', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Constructed', null, 'N', 'admin';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Creole', null, 'N', 'admin';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Dené–Yeniseian', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Digaro', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Dravidian', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'East Bird\'s Head - Sentani', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'East Geelvink Bay', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Eastern Trans-Fly', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Eskimo-Aleut', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Esmeralda-Yaruroan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Fas', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Guaicuruan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Gunwinyguan', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Hibito-Cholon', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Hmong-Mien', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Hokan', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Indo-European', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia, Europe';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'International Auxiliary', id, 'N', 'admin' FROM resdb_region WHERE name = 'World ?';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Iroquoian', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Japonic', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Jarrakan', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Jicaquean', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Jirajaran', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Jivaroan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Kadu', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Kartvelian', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia, Europe';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Katembri-Taruma', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Katukinan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Keres', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Kho-Bwa', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Khoe', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Khoman', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Koreanic', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Kra-Dai', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Kuliak', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Kwomtari', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Kx\'a', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Lakes Plain', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Left May', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Lencan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Limilngan', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Lule-Vilela', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Macro-Jê', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Macro-Otomákoan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Mairasi', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Mande', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Mascoian', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Matacoan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Mayan', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Mirndi', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Misumalpan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Mixe-Zoque', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Mongolic', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Mosetenan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Mura', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Muskogean', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Na-Dene', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Nadahup', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Nambikwaran', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Niger-Congo', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Nilo-Saharan', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Nimboran', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Nivkh', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'North Bougainville', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Northeast Caucasian', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia, Europe';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Northwest Caucasian', id, 'N', 'admin' FROM resdb_region WHERE name = 'Caucasian';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Nyulnyulan', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Ongan', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Oto-Manguean', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Otomákoan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Paezan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Pama-Nyungan', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Paman', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Pano-Tacanan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Peba-Yaguan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Penutian', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Piaroa-Saliban', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Piawi', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Puinavean', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Quechuan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Ramu - Lower Sepik', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Salishan', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Senagi', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Sepik', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Siangic', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Sino-Tibetan', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Siouan', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Skou', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Songhay', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'South Bougainville', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Southern Daly', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Tai-Kadai', null, 'N', 'admin';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Takic', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Tangikic', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Tanoan', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Tequiraca-Canichana', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Timotean', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Tiniguan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Tor-Kwerba', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Torricelli', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Totonacan', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Trans-Fly - Bulaka River', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Trans-Fly-New Guinea', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Tucanoan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Tungusic', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Tupian', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Turkian', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia, Europe';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Turkic', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia, Europe';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Tuu', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Ubangian', id, 'N', 'admin' FROM resdb_region WHERE name = 'Africa';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Uralic', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Uru-Chipaya', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Uto-Aztecan', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Wagaydyic', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Wakashan', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'West New Britain', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'West Papuan', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Western Daly', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Wintuan', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Witotoan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Worrorran', id, 'N', 'admin' FROM resdb_region WHERE name = 'Australia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Xincan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Yanomaman', ID, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Yeniseian', ID, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Yok-Utian', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Yuat', id, 'N', 'admin' FROM resdb_region WHERE name = 'New Guinea';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Yukaghir', id, 'N', 'admin' FROM resdb_region WHERE name = 'Asia';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Yuki-Wappo', id, 'N', 'admin' FROM resdb_region WHERE name = 'North America';

INSERT INTO resdb_language_group (name, region_id, status, created_by)
SELECT 'Zamucoan', id, 'N', 'admin' FROM resdb_region WHERE name = 'South America';

