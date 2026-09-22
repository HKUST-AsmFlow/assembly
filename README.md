# AsmFlow: ARMv7 assembler and emulator


### Instructions currently supported by assembler
### DP
- [x] ADC 
- [x] ADD
- [x] AND
- [x] SUB
- [x] MOV
- [x] MOVW / MOVT
- [x] MVN
- [x] CMP
- [x] EOR
- [x] ORR
- [x] BIC

### Branch
- [x] B
- [x] BL
- [x] BX

### Memory
- [x] LDR
- [x] STR
- [ ] Byte / Halfword / Signed versions
- [x] LDM
- [x] STM

### Misc.
- [x] SVC

### Psuedo
- [ ] ADR
- [ ] LDR (decide MOV+MOVT vs literal pool implementation for large constants)
- [x] PUSH
- [x] POP

### Syscalls currently supported by emulator
Linux ARM EABI convention: syscall number in r7, arguments in r0-r2, result in r0 (the `SVC` immediate is ignored).
- [x] exit (r7 = 1), exit code taken from r0
- [x] write (r7 = 4), fd 1 (stdout) and fd 2 (stderr) only
- [ ] read
