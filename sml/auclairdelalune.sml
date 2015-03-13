
let instrument1 = piano ;
let n = 6;
let m = 12;
let r=24;
let theme = { 1dn , 1dn, 1dn , 2dn , 3db , 2db , 1dn , 3dn , 2dn , 2dn , 1dr } ;
begin
tempo 120;
play (scale c ionian) instrument1 theme;
end;