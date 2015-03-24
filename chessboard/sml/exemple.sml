
let instrument1 = instru piano ;
let n = 6;
let b = 12;
let r=24;
let theme = { 1dn , 1dn, 1dn , 2dn , 3db , 2db , 1dn , 3dn , 2dn , 2dn , 1dr,  ?4 } ;
begin
tempo 120;
play (scale c ionian) (ens instrument1 (instru flute)) theme;
end;