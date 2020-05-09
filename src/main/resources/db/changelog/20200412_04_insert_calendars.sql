--liquibase formatted sql

--changeset author:schulewa

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, status, created_by)
select 'Vikram Samvat', t.id, g.id, 'admin', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Indian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Egyptian calendar', t.id, g.id, 'admin', 'The year is based on the heliacal rising of Sirius (Sothis) and divided into the three seasons of akhet (Inundation), peret (Growth) and shemu (Harvest). The heliacal rising of Sothis returned to the same point in the calendar every 1,460 years (a period called the Sothic cycle)', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Fixed (365 days)' and g.name = 'Egyptian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Umma calendar', t.id, g.id, 'admin', 'Recorded in Neo-Sumerian records (21st century BC), presumably based on older (Ur III) sources', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Mesopotamian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Pentecontad calendar', t.id, g.id, 'admin', 'A Bronze Age calendar in which the year is divided into seven periods of fifty days, with an annual supplement of fifteen or sixteen days for synchronisation with the solar year.', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Mesopotamian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Four Seasons and Eight Nodes', t.id, g.id, 'admin', 'The years is divided into four seasons, and each season is divided into a festival(四立) and three months. The start and middle of each season is the key node of the year.', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Chinese';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Gezer calendar', t.id, g.id, 'admin', 'The years are divided into monthly or bi-monthly periods and attributes to each a duty such as harvest, planting, or tending specific crops.', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunar' and g.name = 'Mesopotamian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Roman calendar', t.id, g.id, 'admin', 'Based on the reforms introduced by Numa Pompilius in c. 713 BC', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Roman';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Six Ancient Calendars', t.id, g.id, 'admin', 'Six classical (Zhou era) calendars: Huangdi (黃帝曆), Zhuanxu (顓頊曆), Xia (夏曆), Yin (殷曆), Zhou''s calendar (周曆) and Lu (魯曆).', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Chinese';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Nisga\'a', t.id, g.id, 'admin', 'The Nisga\'a calendar revolves around harvesting of foods and goods used. The original year followed the various moons throughout the year', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Seasonal / lunisolar' and g.name = 'Indigenous North America';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Haida', t.id, g.id, 'admin', 'The Haida calendar is a lunar calendar broken into two seasons (winter and summer) of six months each with an occasional thirteenth month between seasons.', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunar' and g.name = 'Indigenous North America';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Inuit', t.id, g.id, 'admin', 'The Haida calendar is a lunar calendar broken into two seasons (winter and summer) of six months each with an occasional thirteenth month between seasons', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Seasonal' and g.name = 'Indigenous North America';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Haab''', t.id, g.id, 'admin', '', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Fixed (365 days)' and g.name = 'Pre-Columbian (Maya)';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Tzolk''in', t.id, g.id, 'admin', '', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Fixed (260 days)' and g.name = 'Pre-Columbian (Maya)';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Xiuhpohualli', t.id, g.id, 'admin', '', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Fixed (365 days)' and g.name = 'Pre-Columbian (Aztec)';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Tonalpohualli', t.id, g.id, 'admin', '', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Fixed (365 days)' and g.name = 'Pre-Columbian (Aztec)';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Attic calendar', t.id, g.id, 'admin', 'The year begins with the new moon after the summer solstice. It was introduced by the astronomer Meton in 432 BC. Reconstructed by Academy of Episteme', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Fixed (365 days)' and g.name = 'Hellenic';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Old Persian calendar', t.id, g.id, 'admin', 'Based on earlier Babylonian/Mesopotamian models', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Iranian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Seleucid calendar', t.id, g.id, 'admin', 'Combination of the Babylonian calendar, ancient Macedonian (Hellenic) month names and the Seleucid era', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Hellenic/Babylonian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Genesis Calendar (太初曆)', t.id, g.id, 'admin', 'Introduced the "month without mid-climate is intercalary" rule; based on a solar year of 365​385⁄1539 days and a lunar month of 29​43⁄81 days (19 years=235 months=6939​61⁄81 days)', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Chinese';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Ptolemaic calendar', t.id, g.id, 'admin', 'The Canopic reform of 238 BC introduced the leap year every fourth year later adopted in the Julian calendar. The reform eventually went into effect with the introduction of the "Alexandrian calendar" (or Julian calendar) by Augustus in 26/25 BC, which included a 6th epagomenal day for the first time in 22 BC.', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Egyptian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Julian calendar', t.id, g.id, 'admin', 'Revision of the Roman Republican calendar, in use in the Roman Empire and the Christian Middle Ages, and remains in use as liturgical calendar of Eastern Orthodox Churches.', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Roman';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Coptic calendar', t.id, g.id, 'admin', 'Based on both the Ptolemaic calendar and the Julian calendar', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Egyptian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Ethiopian calendar', t.id, g.id, 'admin', 'The calendar associated with Ethiopian Church, based on the Coptic calendar', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Egyptian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Berber calendar', t.id, g.id, 'admin', 'Julian calendar used for agricultural work', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Julian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Qumran calendrical texts', t.id, g.id, 'admin', 'Description of a division of the year into 364 days, also mentioned in the pseudepigraphical Book of Enoch (the "Enoch calendar").', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Fixed (364 days)' and g.name = '** Unknown **';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Gaulish calendar', t.id, g.id, 'admin', 'Based on both the Old Persian and Seleucid (Hellenic) calendars. Introduced in AD 226, reformed in AD 272, and again several times in the 5th to 7th centuries', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = '** Unknown **';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Chinese Calendar, Dàmíng origin (大明曆)', t.id, g.id, 'admin', 'Created by Zu Chongzhi, most accurate calendar in the world at its invention', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Chinese';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Japanese calendar', t.id, g.id, 'admin', 'Umbrella term for calendars historically and currently used in Japan, in the 6th century derived from the Chinese calendar', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Chinese-derived';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Chinese Calendar, Wùyín origin(戊寅元曆)', t.id, g.id, 'admin', 'First Chinese calendar to use the true moon motion', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Chinese';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Islamic calendar', t.id, g.id, 'admin', 'Based on the observational lunisolar calendars used in Pre-Islamic Arabia. Remains in use for religious purposes in the Islamic world.', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunar' and g.name = '** Unknown **';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Pyu calendar', t.id, g.id, 'admin', 'Traditional calendar of Southeast Asia, in use until the 19th century. Traditionally said to originate in 640 (the calendar era) in Sri Ksetra Kingdom, one of the Burmese Pyu city-states.', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Hindu/Buddhist-derived';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Nepal Sambat', t.id, g.id, 'admin', 'A lunar Buddhist calendar traditional to Nepal, recognition in Nepal in 2008', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunar' and g.name = 'Buddhist/ Hindu';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Byzantine calendar', t.id, g.id, 'admin', 'Julian calendar with Anno Mundi era in use c. 691 to 1728.', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Julian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Armenian calendar', t.id, g.id, 'admin', 'Calendar used in medieval Armenia and as liturgical calendar of the Armenian Apostolic Church. Derived from the Zoroastrian (or related medieval Iranian calendars such as the Sogdian/Choresmian ones[2]). It uses the era AD 552. In modern Armenian nationalism, an alternative era of 2492 BC is sometimes used', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Fixed (365 days)' and g.name = 'Iranian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Bulgar calendar', t.id, g.id, 'admin', 'A reconstruction based on a short 15th-century transcript in Church Slavonic originally proposed by Finnish Slavist Jooseppi Julius Mikkola in 1913. According to the reconstructed calendar, the Bulgars used a 12-year cyclic calendar similar to the one adopted by other Turkic peoples from the Chinese calendar', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = '** Unknown **' and g.name = 'Turkic/Chinese-derived';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Florentine calendar', t.id, g.id, 'admin', 'Variant of the Julian calendar in use in medieval Florence', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Julian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Pisan calendar', t.id, g.id, 'admin', 'Variant of the Julian calendar in use in medieval Pisa', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Julian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Tamil calendar', t.id, g.id, 'admin', 'The Hindu calendar used in Tamil Nadu', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Hindu';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Nepali calendar', t.id, g.id, 'admin', 'One of the Hindu calendars', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Hindu/ Buddhist';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Bengali calendar', t.id, g.id, 'admin', 'Revised in 1987', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Bengali';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Thai lunar calendar', t.id, g.id, 'admin', 'A Buddhist calendar', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Hindu/Buddhist';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Pawukon calendar', t.id, g.id, 'admin', '', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Fixed (210 days)' and g.name = 'Hindu';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Old Icelandic calendar', t.id, g.id, 'admin', 'Partly inspired by the Julian calendar and partly by older Germanic calendar traditions. Leap week calendar based on a year of 364 days.', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = '** Unknown **';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Jalali calendar', t.id, g.id, 'admin', 'A calendar reform commissioned by Sultan Jalal al-Din Malik Shah I', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Iranian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Hebrew calendar', t.id, g.id, 'admin', 'Recorded by Maimonides in the Mishneh Torah, resulting from various reforms and traditions developing since Late Antiquity. The Anno Mundi era gradually replaced the Seleucid era in Rabbinical literature in the 11th century.', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Babylonian/Seleucid-derived';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Tibetan calendar', t.id, g.id, 'admin', 'The Kalacakra, a Buddhist calendar introduced in 13th-century Tibet', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Buddhist/Chinese-derived';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Seasonal Instruction (授时曆)', t.id, g.id, 'admin', 'Based on a solar year of 365.2425 (equal to the Gregorian year)', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Chinese';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Runic calendar', t.id, g.id, 'admin', 'A written representation of the Metonic cycle used in medieval and early modern Sweden, allowing to calculate the dates of the full moons relative to the Julian date. The introduction of the Gregorian calendar in Sweden in 1753 rendered the runic calendars unusable', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Julian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Six Imperial Calendars (ß)', t.id, g.id, 'admin', 'In use 1368-1644', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Chinese';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Incan calendar', t.id, g.id, 'admin', '', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Pre-Columbian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Muisca calendar', t.id, g.id, 'admin', 'Complex lunisolar calendar with three different years, composed of months divided into thirty days. After the Spanish conquest of the Muisca Confederation in present-day central Colombia in 1537 first replaced by the European Julian and as of 1582 the Gregorian calendar.', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Pre-Columbian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Chula Sakarat', t.id, g.id, 'admin', '', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunisolar' and g.name = 'Burmese';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Gregorian calendar', t.id, g.id, 'admin', 'Introduced as a reform of the Julian calendar in the Roman Catholic church, since the 20th century in de facto use worldwide', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Julian-derived';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Javanese calendar', t.id, g.id, 'admin', 'Based on the Hindu calendar using the Saka era (78 CE), but changed to the lunar year following the Islamic calendar', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Lunar' and g.name = 'Islamic influenced';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Seasonal Constitution (时宪历)', t.id, g.id, 'admin', 'First Chinese Calendar to use the true motion of the sun', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Chinese';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Swedish calendar', t.id, g.id, 'admin', 'Part of the controversy surrounding the adoption of the Gregorian calendar, in use 1700–1712.', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Julian-derived';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Astronomical year numbering', t.id, g.id, 'admin', 'A mixture of Julian and Gregorian calendar, giving dates before 1582 in the Julian calendar, and dates after 1582 in the Gregorian calendar, counting 1 BC as year zero, and negative year numbers for 2 BC and earlier', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Julian-derived';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'French Republican Calendar', t.id, g.id, 'admin', 'In use in revolutionary France 1793 to 1805', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Gregorian';

insert into resdb_calendar (name, calendar_type_id, calendar_group_id, owned_by, notes, status, created_by)
select 'Pancronometer', t.id, g.id, 'admin', 'Universal Georgian Calendar proposed by Hugh Jones', 'N', 'admin'
from resdb_calendar_type t, resdb_calendar_group g
where t.name = 'Solar' and g.name = 'Gregorian';

