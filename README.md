# project-study

#项目收集和学习
添加java算法学习代码


## 带依赖打包
`
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <configuration>
        <archive>
            <manifest>
                <mainClass>com.jiang.demo.CpuDemo</mainClass>
            </manifest>
        </archive>
        <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
    </configuration>
</plugin>
`
mvn assembly:assembly

