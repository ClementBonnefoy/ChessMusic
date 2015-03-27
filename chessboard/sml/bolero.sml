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
                         1c3, 7b3, 2c3, 1c3, 7b3, 6b3, 5b3, 4b3, 3b48, ?6,
                         2b18, 3b6, 2b3, 3b3, 4b18, 5b6, 6-b6, 4b4, 5b4,
                         3b4, 2b3, 3b3, 2b3, 1b9, 7a21, 1b3, 7a3, 1b3, 2b3, 3b3, 2b3, 1b3,
                         2b3, 1b3, 7a3, 6a3, 7a3, 6a3, 5a12, 4a33, 5a3, 4a3, 5a3};

let theme2locrien = { 3-a27, 4a3,
                         3a3, 4a3, 2a30, 2a3, 3a3, 2a3, 4a3, 3a3, 2a3, 1a15, ?24 } ;



begin
tempo 1000;
play (scale c ionian) (instru piano) themeA;
play (scale c ionian) (instru piano) theme2myxo;
play (scale c ionian) (instru piano) theme2myxob9b13;
play (scale c ionian) (instru piano) theme2locrien;
end;