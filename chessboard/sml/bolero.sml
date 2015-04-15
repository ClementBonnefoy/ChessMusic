
let themeA= { 1c18 , 7b3, 1c3, 2c3, 1c3, 7b3, 6b3, 1c6, 1c3, 6b3, 1c18,
                          7b3, 1c3, 6b3, 5b3, 3b3, 4b3, 5b27, 4b3, 3b3, 2b3, 3b3, 4b3,
                         5b3, 6b3, 5b27, 6b3, 7b3, 6b3, 5b3, 4b3, 3b3, 2b3, 3b3, 2b3,
                         1b12, 1b3, 2b3, 3b6, 4b6, 2b12, 5b54, ?6, 2c21, 1c3, 7b3,
                         6b3, 7b3, 1c3, 2c3, 1c3, 7b9, 1c3, 7b3, 6b3, 1c3, 7b3, 6b3, 
                         4b9, 4b3, 4b3, 4b6, 6b6, 1c3, 6b3, 7b3, 5b3, 4b6, 4b3, 4b3,
                         4b6, 6b6, 7b3, 5b3, 6b3, 4b3, 2b6, 2b3, 1b3, 2b18, 2b3, 2b3,
                         2b6, 4b6, 6b3, 4b3, 5b3, 3b3, 2b6, 2b3, 1b3, 2b18, 2b3, 1b3,
                         2b6, 3b3, 4b3, 5b27, 4b3, 3b3, 2b3, 1b6, ?6 } ; 
                         
let theme2myxo = { 7b15, 6b3, 5b3, 4b3, 7b3, 1c3, 6b3, 5b3, 7b6, 6b3, 5b3, 7b12,
                          6b3, 7b3, 6b3, 5b18, 4b3, 3b3, 2b3, 3b24, ?6} ;

let theme2myxob9b13 = { 7b6, 1c6,
                         2c12, 2c12, 2c6, 2c6, 2c6, 2c4, 2c4, 2c4, 2c6, 1c3, 7b3, 2c6,
                         1c3, 7b3, 2c3, 1c3, 7b3, 6b3, 5b3, 4b3, 3b48, ?6 };
let themebartok = { 2b18, 3b6, 2b3, 3b3, 4b18, 5b6, 6b6, 4b4, 5b4,
                         3b4, 2b3, 3b3, 2b3, 1b9, 7a21, 1b3, 7a3, 1b3, 2b3, 3b3, 2b3, 1b3,
                         2b3, 1b3, 7a3, 6a3, 7a3, 6a3, 5a12, 4a33};

let theme2locrien = { 5a3, 4a3, 5a3, 3a27, 4a3,
                         3a3, 4a3, 2a30, 2a3, 3a3, 2a3, 4a3, 3a3, 2a3, 1a15, ?24 } ;
                         
let rythme = { 1a6, 1a2, 1a2, 1a2, 1a6, 1a2, 1a2, 1a2, 1a6, 1a6,
					1a6, 1a2, 1a2, 1a2, 1a6, 1a2, 1a2, 1a2, 1a2, 1a2, 1a2, 1a2, 1a2, 1a2};
					
let harmoBasse1 = { 1b12, ?12, 5a12, 1b12, ?12, 5a6, ?6};
let harmoBasse2 = { ?24, 5b12, ?24, 5b6, 5b6};
let harmoAigu1 = { 5b12, 5b12, ?12, 5b12, 5b12, ?12};
let harmoAigu2 = { ?12, 5c12, ?12, ?12, 5c12, ?12};

let myxob9b13 = scale myxolydian {,-,,,,-,} ;
let bartokscale = scale myxolydian {,,,,,-,} ;

let instrument1=ens (instru piano) (instru flute);



begin
tempo 120;
play (scale c ionian) instrument1 themeA;
play (scale c myxolydian) instrument1 theme2myxo;
play (scale c myxob9b13) instrument1 theme2myxob9b13;
play (scale c bartokscale) instrument1 themebartok;
play (scale c locrian) instrument1 theme2locrien;
end;