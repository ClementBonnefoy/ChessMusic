
let instrument1 = instru piano ;
let n = 6;
let b = 12;
let r=24;
let theme = { 1dn , 1dn, 1dn , 2dn , 3db , 2db , 1dn , 3dn , 2dn , 2dn , 1dr} ;
begin
tempo 1000;
play (scale c ionian) instrument1 theme;
play (scale c ionian) (instru flute) theme;
play (scale c ionian) (ens instru flute instrument1) theme;
play (scale c ionian) (chord (instrument1 theme) ((instru flute)
	(sequence ?r theme)));
play (scale c dorian) (instru flute) theme;

end;