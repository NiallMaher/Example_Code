@echo OFF
@echo. %date% at %time% >>PingLog.txt

:REPEAT
@echo.%time% >>PingLog.txt

ping 192.168.1.1 >>PingLog.txt
goto REPEAT