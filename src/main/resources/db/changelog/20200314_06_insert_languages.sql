--liquibase formatted sql

--changeset author:schulewa

--comment list of languages with their associated language family

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Abkhazian', 'аҧсуа бызшәа, аҧсшәа', 'ab', 'abk', 'abk', 'abk', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Abkhaz', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Northwest Caucasian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Afar', 'Afaraf', 'aa', 'aar', 'aar', 'aar', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Afro-Asiatic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Afrikaans', 'Afrikaans', 'af', 'afr', 'afr', 'afr', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Akan', 'Akan', 'ak', 'aka', 'aka', 'aka + 2', 'Y', 'Y', 'N', 'Y', lg.id, 'Twi is [tw/twi], Fanti is [fat]', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Albanian', 'Shqip', 'sq', 'sqi', 'alb', 'sqi + 4', 'Y', 'Y', 'N', 'Y', lg.id, '"Albanian Phylozone" in 639-6', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Amharic', 'አማርኛ', 'am', 'amh', 'amh', 'amh', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Afro-Asiatic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Arabic', 'العربية', 'ar', 'ara', 'ara', 'ara + 29', 'Y', 'Y', 'N', 'Y', lg.id, 'Standard Arabic is [arb]', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Afro-Asiatic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Aragonese', 'aragonés', 'an', 'arg', 'arg', 'arg', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Armenian', 'Հայերեն', 'an', 'arg', 'arg', 'arg', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Հայերէն; ISO 639-3 code "hye" is for Eastern Armenian, "hyw" is for Western Armenian, and "xcl" is for Classical Armenian', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Assamese', 'অসমীয়া', 'as', 'asm', 'asm', 'asm', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Avaric', 'авар мацӀ, магӀарул мацӀ', 'av', 'ava', 'ava', 'ava', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Avar', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Northeast Caucasian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Avestan', 'avesta', 'ae', 'ave', 'ave', 'ancient', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Aymara', 'aymar aru', 'ay', 'aym', 'aym', 'aym + 2', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Aymaran';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Azerbaijani', 'azərbaycan dili', 'az', 'aze', 'aze', 'aze + 2', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Turkian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Bambara', 'bamanankan', 'bm', 'bam', 'bam', 'bam', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Bashkir', 'башҡорт теле', 'ba', 'bak', 'bak', 'bak', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Turkian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Basque', 'euskara, euskera', 'eu', 'eus', 'baq', 'eus', 'Y', 'Y', 'N', 'N', null, '', 'N', 'admin';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Belarusian', 'беларуская мова', 'be', 'bel', 'bel', 'bel', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Bengali', 'বাংলা', 'bn', 'ben', 'ben', 'ben', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Bangla', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Bihari', 'भोजपुरी', 'bh', 'bih', 'bih', 'bih', 'Y', 'Y', 'N', 'N', lg.id, 'collective language code for Bhojpuri, Magahi, and Maithili', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Bislama', 'Bislama', 'bi', 'bis', 'bis', 'bis', 'Y', 'Y', 'N', 'N', lg.id, 'Language formed from English and Ni-Vanuatu, with some French influence.', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Creole';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Bosnian', 'bosanski jezik', 'bs', 'bos', 'bos', 'bos', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Breton', 'brezhoneg', 'br', 'bre', 'bre', 'bre', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Bulgarian', 'български език', 'bg', 'bul', 'bul', 'bul', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Burmese', 'ဗမာစာ', 'my', 'mya', 'bur', 'mya', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Sino-Tibetan';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Catalan', 'català', 'ca', 'cat', 'cat', 'cat', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Valencian', 'valencià', 'ca', 'cat', 'cat', 'cat', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Chamorro', 'Chamoru', 'ch', 'cha', 'cha', 'cha', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Chechen', 'нохчийн мотт', 'ce', 'che', 'che', 'che', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Northeast Caucasian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Chichewa', 'chiCheŵa', 'ny', 'nya', 'nya', 'nya', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Chewa', 'chiCheŵa', 'ny', 'nya', 'nya', 'nya', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Nyanja', 'chinyanja', 'ny', 'nya', 'nya', 'nya', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Chinese', '中文 (Zhōngwén), 汉语, 漢語', 'zh', 'zho', 'chi', 'zho + 16', 'Y', 'Y', 'N', 'N', lg.id, 'macrolanguage', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Sino-Tibetan';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Chuvash', 'чӑваш чӗлхи', 'cv', 'chv', 'chv', 'chv', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Turkian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Cornish', 'Kernewek', 'kw', 'cor', 'cor', 'cor', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Corsican', 'corsu, lingua corsa', 'co', 'cos', 'cos', 'cos', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Cree', 'ᓀᐦᐃᔭᐍᐏᐣ', 'cr', 'cre', 'cre', 'cre + 6', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Algonquian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Croatian', 'hrvatski jezik', 'hr', 'hrv', 'hrv', 'hrv', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Czech', 'čeština, český jazyk', 'cs', 'ces', 'cze', 'ces', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Danish', 'dansk', 'da', 'dan', 'dan', 'dan', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Divehi', 'ދިވެހި', 'dv', 'div', 'div', 'div', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Dhivehi', 'ދިވެހި', 'dv', 'div', 'div', 'div', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Maldivian', 'ދިވެހި', 'dv', 'div', 'div', 'div', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Dutch', 'Nederlands, Vlaams', 'nl', 'nld', 'dut', 'nld', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Flemish', 'Vlaams', 'nl', 'nld', 'dut', 'nld', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Dzongkha', 'རྫོང་ཁ', 'dz', 'dzo', 'dzo', 'dzo', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Sino-Tibetan';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'English', 'English', 'en', 'eng', 'eng', 'eng', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Esperanto', 'Esperanto', 'eo', 'epo', 'epo', 'epo', 'Y', 'Y', 'N', 'N', null, 'constructed, initiated from L.L. Zamenhof, 1887', 'N', 'admin';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Estonian', 'eesti, eesti keel', 'et', 'est', 'est', 'est + 2', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Uralic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Ewe', 'Eʋegbe', 'ee', 'ewe', 'ewe', 'ewe', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Faroese', 'føroyskt', 'fo', 'fao', 'fao', 'fao', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Fijian', 'vosa Vakaviti', 'fi', 'fij', 'fij', 'fij', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Finnish', 'suomi, suomen kieli', 'fi', 'fin', 'fin', 'fin', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Uralic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'French', 'français, langue française', 'fr', 'fra', 'fre', 'fra', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Fulah', 'Fulfulde, Pulaar, Pular', 'ff', 'ful', 'ful', 'ful + 9', 'Y', 'Y', 'N', 'N', lg.id, 'macrolanguage, also known as Fula', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Galician', 'Galego', 'gl', 'glg', 'glg', 'glg', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Georgian', 'ქართული', 'ka', 'kat', 'geo', 'kat', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Kartvelian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'German', 'Deutsch', 'de', 'deu', 'ger', 'deu', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Greek', 'ελληνικά', 'el', 'ell', 'gre', 'ell', 'Y', 'Y', 'N', 'N', lg.id, 'Modern (1453–)', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Guarani', 'Avañe''ẽ', 'gn', 'grn', 'grn', 'grn + 5', 'Y', 'Y', 'N', 'N', lg.id, 'macrolanguage', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Tupian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Gujarati', 'ગુજરાતી', 'gu', 'guj', 'guj', 'guj', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Haitian', 'Kreyòl ayisyen', 'ht', 'hat', 'hat', 'hat', 'Y', 'Y', 'N', 'N', lg.id, 'Haitian Creole', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Creole';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Hausa', '(Hausa) هَوُسَ', 'ha', 'hau', 'hau', 'hau', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Afro-Asiatic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Hebrew', 'עבריתَ', 'he', 'heb', 'heb', 'heb', 'Y', 'Y', 'N', 'N', lg.id, 'Modern Hebrew. Code changed in 1989 from original ISO 639:1988, iw.', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Afro-Asiatic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Herero', 'Otjiherero', 'hZ', 'her', 'her', 'her', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Hindi', 'हिन्दी, हिंदी', 'hi', 'hin', 'hin', 'hin', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Hiri Motu', 'Hiri Motu', 'ho', 'hmo', 'hmo', 'hmo', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Hungarian', 'magyar', 'hu', 'hun', 'hun', 'hun', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Uralic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Interlingua', 'Interlingua', 'ia', 'ina', 'ina', 'ina', 'Y', 'Y', 'N', 'N', lg.id, 'constructed by International Auxiliary Language Association', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Constructed';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Indonesian', 'Bahasa Indonesia', 'id', 'ind', 'ind', 'ind', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Interlingue', '(originally:) Occidental, (after WWII:) Interlingue', 'ie', 'ile', 'ile', 'ile', 'Y', 'Y', 'N', 'N', lg.id, 'constructed by Edgar de Wahl, first published in 1922', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Constructed';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Irish', 'Gaeilge', 'ga', 'gle', 'gle', 'gle', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Igbo', 'Asụsụ Igbo', 'ig', 'ibo', 'ibo', 'ibo', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Inupiac', 'Iñupiaq, Iñupiatun', 'ik', 'ipk', 'ipk', 'ipk + 2', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Eskimo-Aleut';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Ido', 'Ido', 'io', 'ido', 'ido', 'ido', 'Y', 'Y', 'N', 'N', lg.id, 'constructed by De Beaufront, 1907, as variation of Esperanto', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Constructed';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Icelandic', 'Íslenska', 'is', 'isl', 'ice', 'isl', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Italian', 'Italiano', 'it', 'ita', 'ita', 'ita', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Inuktitut', 'ᐃᓄᒃᑎᑐᑦ', 'iu', 'iku', 'iku', 'iku + 2', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Eskimo-Aleut';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Japanese', '日本語 (にほんご)', 'ja', 'jpn', 'jpn', 'jpn', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Japonic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Javanese', 'ꦧꦱꦗꦮ, Basa Jawa', 'jv', 'jav', 'jav', 'jav', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Kalaallisut', 'kalaallisut, kalaallit oqaasii', 'kl', 'kal', 'kal', 'kal', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Eskimo-Aleut';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Greenlandic', 'kalaallisut, kalaallit oqaasii', 'kl', 'kal', 'kal', 'kal', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Eskimo-Aleut';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Kannada', 'ಕನ್ನಡ', 'kn', 'kan', 'kan', 'kan', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Dravidian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Kashmiri', 'कश्मीरी, كشميري‎', 'ks', 'kas', 'kas', 'kas', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Kazakh', 'қазақ тілі', 'kk', 'kaz', 'kaz', 'kaz', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Turkian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Central Khmer', 'ខ្មែរ, ខេមរភាសា, ភាសាខ្មែរ', 'km', 'khm', 'khm', 'khm', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Khmer or Cambodian', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austroasiatic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Kikuyu', 'Gĩkũyũ', 'ki', 'kik', 'kik', 'kik', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Gikuyu', 'Gĩkũyũ', 'ki', 'kik', 'kik', 'kik', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Kinyarwanda', 'Ikinyarwanda', 'rw', 'kin', 'kin', 'kin', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Kirghiz', 'Кыргызча, Кыргыз тили', 'ky', 'kir', 'kir', 'kir', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Turkic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Kyrgyz', 'Кыргызча, Кыргыз тили', 'ky', 'kir', 'kir', 'kir', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Turkic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Komi', 'коми кыв', 'kv', 'kom', 'kom', 'kom + 2', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Uralic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Kongo', 'Kikongo', 'kg', 'kon', 'kon', 'kon + 3', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Korean', '한국어', 'ko', 'kor', 'kor', 'kor', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Koreanic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Kurdish', 'Kurdî, کوردی‎', 'ku', 'kur', 'kur', 'kur + 3', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Kuanyama', 'Kuanyama', 'kj', 'kua', 'kua', 'kua', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Kwanyama', 'Kuanyama', 'kj', 'kua', 'kua', 'kua', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Latin', 'latine, lingua latina', 'la', 'lat', 'lat', 'lat', 'Y', 'Y', 'N', 'N', lg.id, 'ancient', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Luxembourgish', 'Lëtzebuergesch', 'lb', 'ltz', 'ltz', 'ltz', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Letzeburgesch', 'Lëtzebuergesch', 'lb', 'ltz', 'ltz', 'ltz', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Ganda', 'Luganda', 'lg', 'lug', 'lug', 'lug', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Limburgan', 'Limburgs', 'li', 'lim', 'lim', 'lim', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Limburger', 'Limburgs', 'li', 'lim', 'lim', 'lim', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Limburgish', 'Limburgs', 'li', 'lim', 'lim', 'lim', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Lingala', 'Lingála', 'ln', 'lin', 'lin', 'lin', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Lao', 'ພາສາລາວ', 'lo', 'lao', 'lao', 'lao', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Tai-Kadai';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Lithuanian', 'lietuvių kalba', 'lt', 'lit', 'lit', 'lit', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Luba-Katanga', 'Kiluba', 'lu', 'lub', 'lub', 'lub', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Latvian', 'latviešu valoda', 'lv', 'lav', 'lav', 'lav + 2', 'Y', 'Y', 'N', 'N', lg.id, 'macrolanguage', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Manx', 'Gaelg, Gailck', 'gv', 'glv', 'glv', 'glv', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Macedonian', 'македонски јазик', 'mk', 'mkd', 'mac', 'mkd', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Malagasy', 'fiteny malagasy', 'mg', 'mlg', 'mlg', 'mlg + 11', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Malay', 'Bahasa Melayu, ملايو‎ بهاس', 'ms', 'msa', 'may', 'msa + 36', 'Y', 'Y', 'N', 'Y', lg.id, 'Standard Malay is [zsm], Indonesian is [id/ind]', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Malayalam', 'മലയാളം', 'ml', 'mal', 'mal', 'mal', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Dravidian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Maltese', 'Malti', 'mt', 'mlt', 'mlt', 'mlt', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Afro-Asiatic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Maori', 'te reo Māori', 'mi', 'mri', 'mao', 'mri', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Māori', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Marathi', 'मराठी', 'mr', 'mar', 'mar', 'mar', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Marāṭhī', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Marshallese', 'Kajin M̧ajeļ', 'mh', 'mah', 'mah', 'mah', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Mongolian', 'Монгол хэл', 'mn', 'mon', 'mon', 'mon + 2', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Mongolic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Nauru', 'Dorerin Naoero', 'na', 'nau', 'nau', 'nau', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Nauruan', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Navajo', 'Diné bizaad', 'nv', 'nav', 'nav', 'nav', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Dené–Yeniseian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Navaho', 'Diné bizaad', 'nv', 'nav', 'nav', 'nav', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Dené–Yeniseian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'North Ndebele', 'isiNdebele', 'nd', 'nde', 'nde', 'nde', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Northern Ndebele', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Nepali', 'नेपाली', 'ne', 'nep', 'nep', 'nep + 2', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Ndonga', 'Owambo', 'ng', 'ndo', 'ndo', 'ndo', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Norwegian Bokmål', 'Norsk Bokmål', 'nb', 'nob', 'nob', 'nob', 'Y', 'Y', 'N', 'N', lg.id, 'Covered by macrolanguage [no/nor]', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Norwegian Nynorsk', 'Norsk Nynorsk', 'nn', 'nno', 'nno', 'nno', 'Y', 'Y', 'N', 'N', lg.id, 'Covered by macrolanguage [no/nor]', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Norwegian', 'Norsk', 'no', 'nor', 'nor', 'nor', 'Y', 'Y', 'N', 'Y', lg.id, 'Bokmål is [nb/nob], Nynorsk is [nn/nno]', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Sichuan Yi', 'ꆈꌠ꒿ Nuosuhxop', 'ii', 'iii', 'iii', 'iii', 'Y', 'Y', 'N', 'N', lg.id, 'Standard form of Yi languages', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Sino-Tibetan';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Nuosu', 'ꆈꌠ꒿ Nuosuhxop', 'ii', 'iii', 'iii', 'iii', 'Y', 'Y', 'N', 'N', lg.id, 'Standard form of Yi languages', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Sino-Tibetan';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'South Ndebele', 'isiNdebele', 'nr', 'nbl', 'nbl', 'nbl', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Southern Ndebele', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Occitan', 'occitan, lenga d''òc', 'oc', 'oci', 'oci', 'oci', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Ojibwa', 'ᐊᓂᔑᓈᐯᒧᐎᓐ', 'oj', 'oji', 'oji', 'oji + 7', 'Y', 'Y', 'N', 'Y', lg.id, 'macrolanguage, also known as Ojibwe', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Algonquian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Church Slavic', 'ѩзыкъ словѣньскъ', 'cu', 'chu', 'chu', 'chu', 'Y', 'Y', 'N', 'N', lg.id, 'ancient, in use by Orthodox Church. Old Slavonic, Church Slavonic, Old Bulgarian, Old Church Slavonic', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Old Slavonic', 'ѩзыкъ словѣньскъ', 'cu', 'chu', 'chu', 'chu', 'Y', 'Y', 'N', 'N', lg.id, 'ancient, in use by Orthodox Church. Old Slavonic, Church Slavonic, Old Bulgarian, Old Church Slavonic', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Church Slavonic', 'ѩзыкъ словѣньскъ', 'cu', 'chu', 'chu', 'chu', 'Y', 'Y', 'N', 'N', lg.id, 'ancient, in use by Orthodox Church. Old Slavonic, Church Slavonic, Old Bulgarian, Old Church Slavonic', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Old Bulgarian', 'ѩзыкъ словѣньскъ', 'cu', 'chu', 'chu', 'chu', 'Y', 'Y', 'N', 'N', lg.id, 'ancient, in use by Orthodox Church. Old Slavonic, Church Slavonic, Old Bulgarian, Old Church Slavonic', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Old Church Slavonic', 'ѩзыкъ словѣньскъ', 'cu', 'chu', 'chu', 'chu', 'Y', 'Y', 'N', 'N', lg.id, 'ancient, in use by Orthodox Church. Old Slavonic, Church Slavonic, Old Bulgarian, Old Church Slavonic', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Oromo', 'Afaan Oromoo', 'om', 'orm', 'orm', 'orm + 4', 'Y', 'Y', 'N', 'Y', lg.id, 'macrolanguage', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Afro-Asiatic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Oriya', 'ଓଡ଼ିଆ', 'or', 'ori', 'ori', 'ori + 2', 'Y', 'Y', 'N', 'Y', lg.id, 'also known as Odia', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Ossetian', 'ирон æвзаг', 'os', 'oss', 'oss', 'oss', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Ossetic', 'ирон æвзаг', 'os', 'oss', 'oss', 'oss', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Punjabi', 'ਪੰਜਾਬੀ, پنجابی‎', 'pa', 'pan', 'pan', 'pan', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Panjabi', 'ਪੰਜਾਬੀ, پنجابی‎', 'pa', 'pan', 'pan', 'pan', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Pali', 'पालि, पाळि', 'pi', 'pli', 'pli', 'pli', 'Y', 'Y', 'N', 'N', lg.id, 'ancient, also known as Pāli', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Persian', 'فارسی', 'fa', 'fas', 'per', 'fas + 2', 'Y', 'Y', 'N', 'Y', lg.id, 'also known as Farsi', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Polish', 'język polski, polszczyzna', 'pl', 'pol', 'pol', 'pol', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Pashto', 'پښتو', 'ps', 'pus', 'pus', 'pus + 3', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Pushto', 'پښتو', 'ps', 'pus', 'pus', 'pus + 3', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Portugese', 'Português', 'pt', 'por', 'por', 'por', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Quechua', 'Runa Simi, Kichwa', 'qu', 'que', 'que', 'que + 43', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Quechuan';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Romansh', 'Rumantsch Grischun', 'rm', 'roh', 'roh', 'roh', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Rundi', 'Ikirundi', 'rn', 'run', 'run', 'run', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Kirundi', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Romanian', 'Română', 'ro', 'ron', 'rum', 'ron', 'Y', 'Y', 'N', 'N', lg.id, 'The identifiers mo and mol are deprecated, leaving ro and ron (639-2/T) and rum (639-2/B) the current language identifiers to be used for the variant of the Romanian language also known as Moldavian and Moldovan in English and moldave in French. The identifiers mo and mol will not be assigned to different items, and recordings using these identifiers will not be invalid.', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Moldavian', 'Română', 'ro', 'ron', 'rum', 'ron', 'Y', 'Y', 'N', 'N', lg.id, 'The identifiers mo and mol are deprecated, leaving ro and ron (639-2/T) and rum (639-2/B) the current language identifiers to be used for the variant of the Romanian language also known as Moldavian and Moldovan in English and moldave in French. The identifiers mo and mol will not be assigned to different items, and recordings using these identifiers will not be invalid.', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Moldovan', 'Română', 'ro', 'ron', 'rum', 'ron', 'Y', 'Y', 'N', 'N', lg.id, 'The identifiers mo and mol are deprecated, leaving ro and ron (639-2/T) and rum (639-2/B) the current language identifiers to be used for the variant of the Romanian language also known as Moldavian and Moldovan in English and moldave in French. The identifiers mo and mol will not be assigned to different items, and recordings using these identifiers will not be invalid.', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Russian', 'русский', 'ru', 'rus', 'rus', 'rus', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Sanskrit', 'संस्कृतम्', 'sa', 'san', 'san', 'san', 'Y', 'Y', 'N', 'N', lg.id, 'ancient, still spoken, also known as Saṃskṛta', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Sardinian', 'sardu', 'sc', 'srd', 'srd', 'srd + 4', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Sindhi', 'सिन्धी, سنڌي، سندھی‎', 'sd', 'snd', 'snd', 'snd', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Northern Sami', 'Davvisámegiella', 'se', 'sme', 'sme', 'sme', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Uralic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Samoan', 'gagana fa''a Samoa', 'sm', 'smo', 'smo', 'smo', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Sango', 'yângâ tî sängö', 'sg', 'sag', 'sag', 'sag', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Creole';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Serbian', 'српски језик', 'sr', 'srp', 'srp', 'srp', 'Y', 'Y', 'N', 'N', lg.id, 'The ISO 639-2/T code srp deprecated the ISO 639-2/B code scc', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Gaelic', 'Gàidhlig', 'gd', 'gla', 'gla', 'gla', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Scottish Gaelic', 'Gàidhlig', 'gd', 'gla', 'gla', 'gla', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Shona', 'chiShona', 'sn', 'sna', 'sna', 'sna', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Sinhala', 'සිංහල', 'si', 'sin', 'sin', 'sin', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Sinhalese', 'සිංහල', 'si', 'sin', 'sin', 'sin', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Slovak', 'Slovenčina, Slovenský Jazyk', 'sk', 'slk', 'slo', 'slk', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Slovenian', 'Slovenski Jezik, Slovenščina', 'sl', 'slv', 'slv', 'slv', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Slovene', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Somali', 'Soomaaliga, af Soomaali', 'so', 'som', 'som', 'som', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Slovene', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Afro-Asiatic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Southern Sotho', 'Sesotho', 'st', 'sot', 'sot', 'sot', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Spanish', 'Español', 'es', 'spa', 'spa', 'spa', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Castillian', 'Español', 'es', 'spa', 'spa', 'spa', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Sundanese', 'Basa Sunda', 'su', 'sun', 'sun', 'sun', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Swahili', 'Kiswahili', 'sw', 'swa', 'swa', 'swa + 2', 'Y', 'Y', 'N', 'N', lg.id, 'macrolanguage', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Swati', 'SiSwati', 'ss', 'ssw', 'ssw', 'ssw', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Swazi', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Swedish', 'Svenska', 'sv', 'swe', 'swe', 'swe', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Tamil', 'தமிழ்', 'ta', 'tam', 'tam', 'tam', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Dravidian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Telugu', 'తెలుగు', 'te', 'tel', 'tel', 'tel', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Dravidian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Tajik', 'тоҷикӣ, toçikī, تاجیکی‎', 'tg', 'tgk', 'tgk', 'tgk', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Thai', 'ไทย', 'th', 'tha', 'tha', 'tha', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Tai-Kadai';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Tigrinya', 'ትግርኛ', 'ti', 'tir', 'tir', 'tir', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Afro-Asiatic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Tibetan', 'བོད་ཡིག', 'bo', 'bod', 'tib', 'bod', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Standard Tibetan', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Sino-Tibetan';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Turkmen', 'Türkmen, Түркмен', 'tk', 'tuk', 'tuk', 'tuk', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Turkic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Tagalog', 'Wikang Tagalog', 'tl', 'tgl', 'tgl', 'tgl', 'Y', 'Y', 'N', 'N', lg.id, 'Note: Filipino (Pilipino) has the code [fil]', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Tswana', 'Setswana', 'tn', 'tsn', 'tsn', 'tsn', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Tonga', 'Faka Tonga', 'to', 'ton', 'ton', 'ton', 'Y', 'Y', 'N', 'N', lg.id, 'also known as Tongan (Tonga Islands)', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Turkish', 'Türkçe', 'tr', 'tur', 'tur', 'tur', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Turkic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Tsonga', 'Xitsonga', 'ts', 'tso', 'tso', 'tso', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Tatar', 'татар теле, tatar tele', 'tt', 'tat', 'tat', 'tat', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Turkic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Twi', 'Twi', 'tw', 'twi', 'twi', 'twi', 'Y', 'Y', 'N', 'N', lg.id, 'Covered by macrolanguage [ak/aka]', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Tahitian', 'Reo Tahiti', 'ty', 'tah', 'tah', 'tah', 'Y', 'Y', 'N', 'N', lg.id, 'One of the Reo Mā`ohi (languages of French Polynesia)', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austronesian';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Uighur', 'ئۇيغۇرچە‎', 'ug', 'uig', 'uig', 'uig', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Turkic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Uyghur', 'Uyghurche', 'ug', 'uig', 'uig', 'uig', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Turkic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Ukrainian', 'Українська', 'uk', 'ukr', 'ukr', 'ukr', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Urdu', 'اردو', 'ur', 'urd', 'urd', 'urd', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Uzbek', 'Oʻzbek, Ўзбек, أۇزبېك‎', 'uz', 'uzb', 'uzb', 'uzb + 2', 'Y', 'Y', 'Y', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Turkic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Venda', 'Tshivenḓa', 've', 'ven', 'ven', 'ven', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Vietnamese', 'Tiếng Việt', 'vi', 'vie', 'vie', 'vie', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Austroasiatic';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Volapük', 'Volapük', 'vo', 'vol', 'vol', 'vol', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Constructed';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Walloon', 'Walon', 'wa', 'wln', 'wln', 'wln', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Welsh', 'Cymraeg', 'cy', 'cym', 'wel', 'cym', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Wolof', 'Wollof', 'wo', 'wol', 'wol', 'wol', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Western Frisian', 'Frysk', 'fy', 'fry', 'fry', 'fry', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Xhosa', 'isiXhosa', 'xh', 'xho', 'xho', 'xho', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Yiddish', 'ייִדיש', 'yi', 'yid', 'yid', 'yid + 2', 'Y', 'Y', 'N', 'Y', lg.id, 'Changed in 1989 from original ISO 639:1988, ji.[1]', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Indo-European';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Yoruba', 'Yorùbá', 'yo', 'yor', 'yor', 'yor', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Zhuang', 'Saɯ cueŋƅ', 'za', 'zha', 'zha', 'zha + 16', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Tai-Kadai';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Chuang', 'Saw cuengh', 'za', 'zha', 'zha', 'zha + 16', 'Y', 'Y', 'N', 'Y', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Tai-Kadai';

INSERT INTO resdb_language (name, native_name, iso_6391_code_1, iso_6392_code_alpha_2t, iso_6392_code_alpha_2b, iso_6392_code_alpha_3, deciphered, living, constructed, macroLanguage, language_group_id, notes, status, created_by)
SELECT 'Zulu', 'isiZulu', 'zu', 'zul', 'zul', 'zul', 'Y', 'Y', 'N', 'N', lg.id, '', 'N', 'admin' FROM  resdb_language_group lg
WHERE lg.name = 'Niger-Congo';

