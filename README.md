# AsmFlow: ARMv7 assembler and emulator


### Instructions currently supported by assembler
### DP
- [x] ADC 
- [x] ADD
- [x] AND
- [x] SUB
- [x] MOV
- [ ] MOVW / MOVT
- [x] MVN
- [ ] CMP
- [ ] EOR
- [ ] ORR
- [ ] BIC

### Branch
- [x] B
- [x] BL
- [ ] BX

### Memory
- [ ] LDR
- [ ] STR
- [ ] Byte / Halfword / Signed versions
- [ ] LDM
- [ ] STM

### Misc.
- [ ] SVC

### Psuedo
- [ ] ADR
- [ ] LDR (decide MOV+MOVT vs literal pool implementation for large constants)
- [ ] PUSH
- [ ] POP
